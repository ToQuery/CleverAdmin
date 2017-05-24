package com.toquery.cleverweb.web.controller.user;


import com.toquery.cleverweb.core.entity.vo.AuthorizationToken;
import com.toquery.cleverweb.entity.dto.UserLogin;
import com.toquery.cleverweb.service.IAuthorizationTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Resource
    @Qualifier("authorizationTokenServiceImpl")
    private IAuthorizationTokenService authorizationTokenService;

    @PostMapping("/login")
    public ResponseEntity UserLogin(@RequestBody UserLogin userLogin) {
        AuthorizationToken loginSuccess = authorizationTokenService.getToken(userLogin.getUserName(), userLogin.getPassword());
        return ResponseEntity.ok(loginSuccess);
    }
}
