package com.toquery.cleverweb.web.controller.token;

import com.toquery.cleverweb.core.entity.vo.AuthorizationToken;
import com.toquery.cleverweb.service.IAuthorizationTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/5/24 13:29
 */
@RestController
@RequestMapping("/token")
public class AuthorizationTokenController {


    @Resource
    @Qualifier("authorizationTokenServiceImpl")
    private IAuthorizationTokenService authorizationTokenService;

    @GetMapping(value = "/refresh")
    public ResponseEntity refreshAndGetAuthenticationToken(@RequestHeader(value = "${jwt.header}") String token) throws AuthenticationException {
        String refreshedToken = authorizationTokenService.refreshToken(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new AuthorizationToken(refreshedToken));
        }
    }
}
