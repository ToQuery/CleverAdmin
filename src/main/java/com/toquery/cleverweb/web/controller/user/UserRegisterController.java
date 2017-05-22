package com.toquery.cleverweb.web.controller.user;

import com.toquery.cleverweb.entity.po.TbSysUser;
import com.toquery.cleverweb.service.ISysUserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ToQuery
 * @version V1.0
 */

@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Resource
    private ISysUserService sysUserService;

    @PostMapping(value = "register")
    public TbSysUser register(@RequestBody TbSysUser registerUser) throws AuthenticationException {
        return sysUserService.registerUser(registerUser);
    }
}
