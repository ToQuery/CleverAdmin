package com.toquery.cleverweb.web.controller.user;


import com.toquery.cleverweb.entity.dto.UserLogin;
import com.toquery.cleverweb.entity.vo.LoginSuccess;
import com.toquery.cleverweb.service.ISysUserService;
import com.toquery.cleverweb.web.controller.auth.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Resource
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity UserLogin(@RequestBody UserLogin userLogin) {
        LoginSuccess loginSuccess = authService.login(userLogin.getUserName(), userLogin.getPassword());
        return ResponseEntity.ok(loginSuccess);
    }
}
