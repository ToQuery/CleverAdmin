package com.toquery.cleverweb.web.controller.auth;


import com.toquery.cleverweb.entity.po.TbSysUser;
import com.toquery.cleverweb.entity.vo.LoginSuccess;

public interface AuthService {
    TbSysUser register(TbSysUser userToAdd);

    LoginSuccess login(String username, String password);

    String refresh(String oldToken);
}
