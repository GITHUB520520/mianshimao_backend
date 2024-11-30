package com.project.interview.manager;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.project.interview.common.ErrorCode;
import com.project.interview.constant.SystemConstant;
import com.project.interview.exception.BusinessException;
import com.project.interview.model.entity.User;
import com.project.interview.model.enums.UserRoleEnum;
import com.project.interview.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CrawlerDetectManager {

    @Resource
    private UserService userService;

    @NacosInjected
    private ConfigService configService;

    @Value("${nacos.config.data-id}")
    private String dataId;

    @Value("${nacos.config.group}")
    private String group;

    @Resource
    private CounterManager counterManager;

    @NacosValue(value = "${warnCount}", autoRefreshed = true)
    private int WARN_COUNT;

    @NacosValue(value = "${banCount}" , autoRefreshed = true)
    private int BAN_COUNT;

    @NacosValue(value = "${timeInterval}" , autoRefreshed = true)
    private int timeInterval;

    @NacosValue(value = "${expireTime}" , autoRefreshed = true)
    private int expireTime;

    @Resource
    private NacosManager nacosManager;

    /**
     * 检测操作是否过于频繁（爬虫）
     * @param loginUserId
     * @param key
     */
    public void crawlerDetect(String key, long loginUserId, String remoteAddr) throws NacosException {
        log.info("warnCount is {}, banCount is {}, timeInterval is {}, expireTime is {}",WARN_COUNT, BAN_COUNT, timeInterval, expireTime);
        if (loginUserId <= 0) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        long count = counterManager.incrAndGetCounter(key, timeInterval, TimeUnit.MINUTES, expireTime);
        if (count >= BAN_COUNT){
            StpUtil.kickout(loginUserId);
            User user = new User();
            user.setId(loginUserId);
            user.setUserRole(UserRoleEnum.BAN.getValue());
            userService.updateById(user);
            nacosManager.addBlackIp(remoteAddr);
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "操作过于频繁，已被封禁!");
        }
        if (count == WARN_COUNT){
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "操作过于频繁!");
        }
    }

    /**
     * 对刷量的ip进行封禁警告
     * @param key
     * @param remoteAddr
     * @throws NacosException
     */
    public void crawlerIpDetect(String key, String remoteAddr) throws NacosException {
        log.info("warnCount is {}, banCount is {}, timeInterval is {}, expireTime is {}",WARN_COUNT, BAN_COUNT, timeInterval, expireTime);
        long count = counterManager.incrAndGetCounter(key, timeInterval, TimeUnit.MINUTES, expireTime);
        if (count >= BAN_COUNT){
            nacosManager.addBlackIp(remoteAddr);
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "操作过于频繁，已被封禁!");
        }
        if (count == WARN_COUNT){
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "操作过于频繁!");
        }
    }
}
