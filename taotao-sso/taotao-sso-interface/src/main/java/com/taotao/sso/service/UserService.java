package com.taotao.sso.service;

import com.taotao.manager.model.User;

public interface UserService {
    Boolean checkUser(String param, Integer type);

    String selectByTicket(String ticket);

    int register(User user);

    User login(User user);
}
