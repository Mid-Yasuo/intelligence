package com.einstein.albert.intelligence.common.aop;

import com.einstein.albert.intelligence.common.ContentHolder;
import com.einstein.albert.intelligence.common.configuration.TokenCacheClient;
import com.einstein.albert.intelligence.entity.TokenCache;
import com.einstein.albert.intelligence.entity.annotation.RequireAuthentication;
import com.einstein.albert.intelligence.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证切面
 *
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Aspect
@Component
@Slf4j
public class AuthenticationAop implements Ordered {

    private HttpServletRequest request;

    private TokenCacheClient tokenCacheClient;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setTokenCacheClient(TokenCacheClient tokenCacheClient) {
        this.tokenCacheClient = tokenCacheClient;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Pointcut("@annotation(com.einstein.albert.intelligence.entity.annotation.RequireAuthentication)")
    public void authenticationPoint() {

    }

    @Before("authenticationPoint()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequireAuthentication requireAuthentication = signature.getMethod().getAnnotation(RequireAuthentication.class);
        if (requireAuthentication.authentication()) {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isBlank(token)) {
                token = request.getParameter("auth");
            }
            TokenCache tokenCache = tokenCacheClient.handRefreshCacheToken(token);
            ContentHolder.setUserTokenCache(tokenCache);
            log.info("tokenCache:{}", JsonUtils.toJson(tokenCache));
        }
    }

    @After("authenticationPoint()")
    public void after() {
        ContentHolder.cleanUserTokenCache();
    }
}
