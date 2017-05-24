package com.toquery.cleverweb.core.token;

import com.toquery.cleverweb.dao.jpa.ITbSysUserDao;
import com.toquery.cleverweb.entity.po.TbSysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private ITbSysUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbSysUser user = userDao.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
