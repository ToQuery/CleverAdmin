package com.toquery.cleverweb.web.controller.auth;

import com.toquery.cleverweb.common.util.UuidUtil;
import com.toquery.cleverweb.core.entity.dto.JWTUserDetails;
import com.toquery.cleverweb.core.secruity.JwtTokenUtil;
import com.toquery.cleverweb.dao.jpa.ITbSysUserDao;
import com.toquery.cleverweb.entity.po.TbSysUser;
import com.toquery.cleverweb.entity.vo.LoginSuccess;
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

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;

    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private ITbSysUserDao userDao;



    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, ITbSysUserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDao = userDao;
    }

    @Override
    public TbSysUser register(TbSysUser userToAdd) {
        final String username = userToAdd.getUserName();
        if (userDao.findByUserName(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
//        userToAdd.setLastPasswordResetDate(new Date());
//        userToAdd.setRoles(asList("ROLE_USER"));
        userToAdd.setUserId(UuidUtil.get32UUID());
        return userDao.save(userToAdd);
    }

    @Override
    public LoginSuccess login(String userName, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userName, password);
        // Perform the security
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new LoginSuccess(userName, "CleverWeb", token);
    }

    @Override
    public String refresh(String oldToken) {
        String username = jwtTokenUtil.getUsernameFromToken(oldToken);
        JWTUserDetails user = (JWTUserDetails) userDetailsService.loadUserByUsername(username);
       /* if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }*/
        if (jwtTokenUtil.canTokenBeRefreshed(oldToken, null)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
