package com.project.interview.saToken;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.project.interview.constant.UserConstant;
import com.project.interview.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.project.interview.constant.UserConstant.USER_LOGIN_STATE;

@Component
public class stpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> res = new ArrayList<>();
        res.add(UserConstant.DEFAULT_ROLE);
        res.add(UserConstant.ADMIN_ROLE);
        res.add(UserConstant.BAN_ROLE);
        return res;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        User user = (User)StpUtil.getSessionByLoginId(o).get(USER_LOGIN_STATE);
        return Collections.singletonList(user.getUserRole());
    }
}
