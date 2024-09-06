package com.einstein.intelligence.configuration.auth;

import com.einstein.intelligence.configuration.auth.AuthContentHolder;
import com.einstein.intelligence.common.Constant;
import com.einstein.intelligence.configuration.TokenCacheClient;
import com.einstein.intelligence.entity.TokenCache;
import com.einstein.intelligence.entity.annotation.RequireAuthentication;
import com.einstein.intelligence.entity.constant.BusinessEnum;
import com.einstein.intelligence.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/5
 */
@Slf4j
@Configuration
public class AuthInterceptor implements HandlerInterceptor {

    private TokenCacheClient tokenCacheClient;

    @Autowired
    public void setTokenCacheClient(TokenCacheClient tokenCacheClient) {
        this.tokenCacheClient = tokenCacheClient;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handle = (HandlerMethod) handler;
            Method method = handle.getMethod();
            RequireAuthentication annotation = method.getAnnotation(RequireAuthentication.class);
            if (Objects.isNull(annotation) || annotation.authentication()) {
                return checkUserAuth(request, annotation);
            }
            return true;
        }
        return true;
    }

    private boolean checkUserAuth(HttpServletRequest request, RequireAuthentication annotation) {
        String authorization = request.getHeader(Constant.AUTH_HEAD);
        if (StringUtils.isBlank(authorization)) {
            throw new BusinessException(BusinessEnum.USER_TOKEN_EMPTY);
        }
        TokenCache tokenCache = tokenCacheClient.refreshCacheToken(authorization);
        AuthContentHolder.setUserTokenCache(tokenCache);
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) {
        AuthContentHolder.cleanUserTokenCache();
    }
}
