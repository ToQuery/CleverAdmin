package com.toquery.cleverweb.service;

import com.toquery.cleverweb.core.entity.vo.AuthorizationToken;
import org.springframework.stereotype.Service;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/5/24 13:29
 */
@Service
public interface IAuthorizationTokenService {

    AuthorizationToken getToken(String username, String password);

    String refreshToken(String oldToken);
}
