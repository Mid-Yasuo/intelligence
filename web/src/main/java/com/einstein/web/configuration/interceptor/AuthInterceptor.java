package com.einstein.web.configuration.interceptor;

import com.einstein.common.configuation.AuthContentHolder;
import com.einstein.common.constant.BusinessEnum;
import com.einstein.common.constant.Constant;
import com.einstein.common.entity.TokenCache;
import com.einstein.common.entity.exception.BusinessException;
import com.einstein.common.entity.annotation.RequireAuthentication;
import com.einstein.web.service.authentication.TokenService;
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

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
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
        TokenCache tokenCache = tokenService.refreshCacheToken(authorization);
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
