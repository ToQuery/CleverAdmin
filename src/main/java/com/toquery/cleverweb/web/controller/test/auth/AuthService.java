package com.toquery.cleverweb.web.controller.test.auth;


import com.toquery.cleverweb.entity.po.TbSysUser;

public interface AuthService {
    TbSysUser register(TbSysUser userToAdd);

    String login(String username, String password);

    String refresh(String oldToken);
}
