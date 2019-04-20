package com.taotao.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.org.apache.regexp.internal.RE;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;


    /**
     *  检验数据是否存在
     * @param param
     * @param type
     * @return
     */
    @GetMapping(value = "/check/{param}/{type}")
    public ResponseEntity<String> check(@PathVariable(value = "param")String param,
             @PathVariable(value = "type")Integer type,
             @RequestParam(value = "callback", required = false, defaultValue = "")String callback) {
        String result = "";
        try {
            Boolean bo = userService.checkUser(param, type);
            if (callback != null && !"".equals(callback)) {
                result += callback + "(" + bo + ")";
            } else {
                result += "" + bo;
            }
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping(value = "/{ticket}")
    public ResponseEntity<String> ticket(@PathVariable(value = "ticket")String ticket) {
        try {
            String result = userService.selectByTicket(ticket);
            if (StringUtils.isNotBlank(result)) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
