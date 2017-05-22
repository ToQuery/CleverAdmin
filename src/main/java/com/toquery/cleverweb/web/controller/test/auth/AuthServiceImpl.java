package com.toquery.cleverweb.web.controller.test.auth;

import com.toquery.cleverweb.core.entity.dto.JWTUserDetails;
import com.toquery.cleverweb.core.secruity.JwtTokenUtil;
import com.toquery.cleverweb.dao.jpa.ITbSysUserDao;
import com.toquery.cleverweb.entity.po.TbSysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static java.util.Arrays.asList;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;

    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private ITbSysUserDao userDao;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, ITbSysUserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDao = userDao;
    }

    @Override
    public TbSysUser register(TbSysUser userToAdd) {
        final String username = userToAdd.getNickName();
        if (userDao.findByUserName(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
//        userToAdd.setLastPasswordResetDate(new Date());
//        userToAdd.setRoles(asList("ROLE_USER"));
        return userDao.save(userToAdd);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JWTUserDetails user = (JWTUserDetails) userDetailsService.loadUserByUsername(username);
       /* if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }*/
        if (jwtTokenUtil.canTokenBeRefreshed(token, null)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
