package com.toquery.cleverweb.web.controller.auth;

import com.toquery.cleverweb.core.secruity.JwtAuthenticationRequest;
import com.toquery.cleverweb.core.secruity.JwtAuthenticationResponse;
import com.toquery.cleverweb.entity.po.TbSysUser;
import com.toquery.cleverweb.entity.vo.LoginSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        LoginSuccess token = authService.login(authenticationRequest.getUserName(), authenticationRequest.getPassword());
        // Return the token
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public TbSysUser register(@RequestBody TbSysUser addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }
}
