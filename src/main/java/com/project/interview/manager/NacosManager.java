package com.project.interview.manager;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class NacosManager {

    @NacosInjected
    private ConfigService configService;

    @Value("${nacos.config.data-id}")
    private String dataId;

    @Value("${nacos.config.group}")
    private String group;

    /**
     * 将违规的 ip 地址进行封禁
     * @param ip
     * @return
     * @throws NacosException
     */
    public boolean addBlackIp(String ip) throws NacosException {
        String content = configService.getConfig(dataId, group, 5000);
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(content, Map.class);
        // 获取 ip 黑名单
        List<String> blackIpList = (List<String>) map.get("blackIpList");
        blackIpList.add(ip);
        map.put("blackIpList", blackIpList);
        String configInfo = yaml.dump(map);
        boolean isPublishOk = configService.publishConfig(dataId, group, configInfo, String.valueOf(ConfigType.YAML));
        return isPublishOk;
    }
}
