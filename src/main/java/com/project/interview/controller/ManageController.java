package com.project.interview.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.api.exception.NacosException;
import com.project.interview.common.BaseResponse;
import com.project.interview.common.BlackIpRequest;
import com.project.interview.common.ErrorCode;
import com.project.interview.common.ResultUtils;
import com.project.interview.exception.ThrowUtils;
import com.project.interview.manager.NacosManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @Resource
    private NacosManager nacosManager;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @GetMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @GetMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @PostMapping("/addBlackIp")
    public BaseResponse<Boolean> addBlackIp(@RequestBody BlackIpRequest blackIpRequest) throws NacosException {
        ThrowUtils.throwIf(blackIpRequest == null || StrUtil.isBlank(blackIpRequest.getBlackIp()), ErrorCode.PARAMS_ERROR);
        boolean b = nacosManager.addBlackIp(blackIpRequest.getBlackIp());
        return ResultUtils.success(b);
    }

}
