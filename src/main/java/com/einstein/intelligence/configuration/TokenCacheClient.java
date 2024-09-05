package com.einstein.intelligence.configuration;

import com.einstein.intelligence.common.Constant;
import com.einstein.intelligence.entity.TokenCache;
import com.einstein.intelligence.entity.annotation.DistributedLock;
import com.einstein.intelligence.entity.constant.BusinessEnum;
import com.einstein.intelligence.entity.po.User;
import com.einstein.intelligence.exception.BusinessException;
import com.einstein.intelligence.exception.authentication.AuthenticationException;
import com.einstein.intelligence.service.UserService;
import com.einstein.intelligence.util.IpUtils;
import com.einstein.intelligence.util.RandomUtils;
import com.einstein.intelligence.util.RedisUtils;
import com.einstein.intelligence.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Component
public class TokenCacheClient {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_PATTERN);

    @Value("${userAuth.tokenDuration:3600}")
    private int tokenDuration;

    private HttpServletRequest request;

    private UserService userService;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @DistributedLock(keyPrefix = "handleInitCacheToken:", keyValue = "#user.username")
    public String handleInitCacheToken(User user) {
        String token = RandomUtils.numRandom(20);
        LocalDateTime now = LocalDateTime.now();
        TokenCache tokenCache = new TokenCache()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setToken(token)
                .setLoginIp(IpUtils.getRequestIp(request))
                .setUserAgent(request.getHeader(HttpHeaders.USER_AGENT))
                .setLoginTime(now.format(DATE_TIME_FORMATTER));
        RedisUtils.set(Constant.USER_TOKEN_CACHE + token, tokenCache, tokenDuration);
        return token;
    }

    public TokenCache refreshCacheToken(String token) {
        TokenCache tokenCache = (TokenCache) RedisUtils.getValue(Constant.USER_TOKEN_CACHE + token);
        if (tokenCache == null) {
            throw new AuthenticationException("", BusinessEnum.USER_TOKEN_EXPIRE);
        }
        User user = userService.getById(tokenCache.getUserId());
        if (Objects.isNull(user)) {
            throw new BusinessException(BusinessEnum.USER_NOT_FOUND);
        }
        UserUtils.checkUser(user);
        RedisUtils.set(Constant.USER_TOKEN_CACHE + token, tokenCache);
        return tokenCache;
    }
}
