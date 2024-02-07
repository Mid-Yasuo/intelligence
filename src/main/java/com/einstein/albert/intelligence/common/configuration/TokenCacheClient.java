package com.einstein.albert.intelligence.common.configuration;

import com.einstein.albert.intelligence.entity.TokenCache;
import com.einstein.albert.intelligence.entity.annotation.DistributedLock;
import com.einstein.albert.intelligence.entity.constant.BusinessEnum;
import com.einstein.albert.intelligence.entity.constant.CommonConst;
import com.einstein.albert.intelligence.entity.po.AuthUser;
import com.einstein.albert.intelligence.exception.authentication.AuthenticationException;
import com.einstein.albert.intelligence.exception.authentication.UnauthorizedException;
import com.einstein.albert.intelligence.util.IpUtils;
import com.einstein.albert.intelligence.util.RandomUtils;
import com.einstein.albert.intelligence.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@RefreshScope
@Component
public class TokenCacheClient {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(CommonConst.DATE_FORMAT_PATTERN);

    @Value("${userAuth.tokenDuration:3600}")
    private int tokenDuration;

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @DistributedLock(keyPrefix = "handleInitCacheToken:", keyValue = "#authUser.username")
    public String handleInitCacheToken(AuthUser authUser) {
        String token = RandomUtils.numRandom(20);
        LocalDateTime now = LocalDateTime.now();
        TokenCache tokenCache = new TokenCache()
                .setUserId(authUser.getId())
                .setUsername(authUser.getUsername())
                .setToken(token)
                .setLoginIp(IpUtils.getRequestIp(request))
                .setUserAgent(request.getHeader(HttpHeaders.USER_AGENT))
                .setLoginTime(now.format(DATE_TIME_FORMATTER));
        RedisUtils.set(CommonConst.USER_TOKEN_CACHE + token, tokenCache, tokenDuration);
        return token;
    }

    public TokenCache handRefreshCacheToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new UnauthorizedException(BusinessEnum.USER_TOKEN_EMPTY.getCode(), BusinessEnum.USER_TOKEN_EMPTY.getMessage());
        }
        TokenCache tokenCache = (TokenCache) RedisUtils.getAndSetValue(CommonConst.USER_TOKEN_CACHE + token, tokenDuration);
        if (tokenCache == null) {
            throw new AuthenticationException("", BusinessEnum.USER_TOKEN_EXPIRE);
        }
        return tokenCache;
    }
}
