package com.einstein.web.service.authentication;

import com.einstein.common.entity.TokenCache;
import com.einstein.database.entity.po.User;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/7
 */
public interface TokenService {

    String handleInitCacheToken(User user);

    TokenCache refreshCacheToken(String token);
}
