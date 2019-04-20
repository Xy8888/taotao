package com.taotao.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.http.CookieUtils;
import com.taotao.manager.model.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterAbortException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @PostMapping(value = "/doRegister")
    public Map<String, Object> register(User user) {
        int acount = userService.register(user);
        Map<String, Object> dataMap = new HashMap<>();
        if (acount > 0) {
            dataMap.put("status", 200);
        }
        return dataMap;
    }

    @PostMapping(value = "/doLogin")
    public Map<String, Object> login(User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            User loginUser = userService.login(user);
            if (loginUser != null) {
                String key = user.getUsername();
                CookieUtils.setCookie(request, response, "UserLoginKey", key, 3600, "UTF-8");
                dataMap.put("status", 200);
                dataMap.put("success", "http://www.taotao.com");
            }
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("status", 500);
        }
        return dataMap;
    }
}
