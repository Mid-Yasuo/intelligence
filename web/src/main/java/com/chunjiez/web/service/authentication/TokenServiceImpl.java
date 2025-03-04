package com.chunjiez.web.service.authentication;

import com.chunjiez.common.constant.BusinessCode;
import com.chunjiez.common.constant.Constant;
import com.chunjiez.common.entity.TokenCache;
import com.chunjiez.common.entity.annotation.DistributedLock;
import com.chunjiez.common.entity.annotation.GetExecutionTime;
import com.chunjiez.common.entity.exception.BusinessException;
import com.chunjiez.common.entity.exception.authentication.AuthenticationException;
import com.chunjiez.common.util.RandomUtils;
import com.chunjiez.database.entity.po.User;
import com.chunjiez.business.service.user.UserService;
import com.chunjiez.business.util.RedisUtils;
import com.chunjiez.web.util.IpUtils;
import com.chunjiez.web.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/7
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

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

    @Override
    @GetExecutionTime
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
                .setLoginTime(now.format(DATE_TIME_FORMATTER))
                .setClientId(user.getClientId())
                .setClientChildren(Collections.singletonList(user.getClientId()));
        RedisUtils.set(Constant.USER_TOKEN_CACHE + token, tokenCache, tokenDuration);
        return token;
    }

    @Override
    @GetExecutionTime
    public TokenCache refreshCacheToken(String token) {
        TokenCache tokenCache = (TokenCache) RedisUtils.getValue(Constant.USER_TOKEN_CACHE + token);
        if (tokenCache == null) {
            throw new AuthenticationException("", BusinessCode.USER_TOKEN_EXPIRE);
        }
        User user = userService.getById(tokenCache.getUserId());
        if (Objects.isNull(user)) {
            throw new BusinessException(BusinessCode.USER_NOT_FOUND);
        }
        UserUtils.checkUser(user);
        RedisUtils.set(Constant.USER_TOKEN_CACHE + token, tokenCache);
        return tokenCache;
    }
}
