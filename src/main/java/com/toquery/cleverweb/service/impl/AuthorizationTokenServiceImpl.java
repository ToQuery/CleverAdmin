package com.toquery.cleverweb.service.impl;

import com.toquery.cleverweb.core.entity.dto.JWTUserDetails;
import com.toquery.cleverweb.core.entity.vo.AuthorizationToken;
import com.toquery.cleverweb.core.token.JwtTokenUtil;
import com.toquery.cleverweb.service.IAuthorizationTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/5/24 13:57
 */
@Service
public class AuthorizationTokenServiceImpl implements IAuthorizationTokenService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthorizationToken getToken(String userName, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userName, password);
        // 执行security
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 重新加载密码安全后,所以我们可以生成令牌
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthorizationToken(userName, token);
    }

    @Override
    public String refreshToken(String oldToken) {
        String username = jwtTokenUtil.getUsernameFromToken(oldToken);
        JWTUserDetails user = (JWTUserDetails) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(oldToken, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
