package com.toquery.cleverweb.core.secruity;

import com.toquery.cleverweb.core.entity.dto.JWTUserDetails;
import com.toquery.cleverweb.entity.po.TbSysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JWTUserDetails create(TbSysUser user) {
        return new JWTUserDetails(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                null
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

