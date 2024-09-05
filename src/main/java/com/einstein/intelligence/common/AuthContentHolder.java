package com.einstein.intelligence.common;

import com.einstein.intelligence.entity.TokenCache;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/8/2
 */
public class AuthContentHolder {

    private static final ThreadLocal<TokenCache> AUTH_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUserTokenCache(TokenCache token) {
        AUTH_THREAD_LOCAL.set(token);
    }

    public static TokenCache getUserTokenCache() {
        return AUTH_THREAD_LOCAL.get();
    }

    public static void cleanUserTokenCache() {
        AUTH_THREAD_LOCAL.remove();
    }
}
