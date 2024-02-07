package com.einstein.albert.intelligence.service.authentication;

import com.einstein.albert.intelligence.common.ContentHolder;
import com.einstein.albert.intelligence.common.configuration.TokenCacheClient;
import com.einstein.albert.intelligence.dao.AuthUserDao;
import com.einstein.albert.intelligence.entity.TokenCache;
import com.einstein.albert.intelligence.entity.constant.BusinessEnum;
import com.einstein.albert.intelligence.entity.constant.UserStatus;
import com.einstein.albert.intelligence.entity.po.AuthUser;
import com.einstein.albert.intelligence.entity.vo.request.LoginRequest;
import com.einstein.albert.intelligence.entity.vo.response.OnlineUser;
import com.einstein.albert.intelligence.exception.BusinessException;
import com.einstein.albert.intelligence.exception.authentication.AuthenticationException;
import com.einstein.albert.intelligence.exception.authentication.UnauthorizedException;
import com.einstein.albert.intelligence.util.DigesterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthUserDao authUserDao;

    private TokenCacheClient tokenCacheClient;

    @Autowired
    public void setAuthUserDao(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Autowired
    public void setTokenCacheClient(TokenCacheClient tokenCacheClient) {
        this.tokenCacheClient = tokenCacheClient;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        AuthUser authUser = authUserDao.selectByUsername(loginRequest.getUsername());
        if (authUser == null) {
            throw new AuthenticationException(loginRequest.getUsername(), BusinessEnum.USER_PASSWORD_ERROR);
        }
        checkUser(authUser);
        String password = authUser.getPassword();
        boolean match = DigesterUtils.match(loginRequest.getPassword(), authUser.getSalt(), password);
        if (BooleanUtils.isFalse(match)) {
            throw new AuthenticationException(loginRequest.getUsername(), BusinessEnum.USER_PASSWORD_ERROR);
        }
        return tokenCacheClient.handleInitCacheToken(authUser);
    }

    @Override
    public OnlineUser getCurrentUser() {
        TokenCache tokenCache = ContentHolder.getUserTokenCache();
        if (tokenCache == null) {
            throw new UnauthorizedException(BusinessEnum.USER_NOT_LOGIN.getCode(), BusinessEnum.USER_NOT_LOGIN.getMessage());
        }

        long userId = tokenCache.getUserId();
        AuthUser authUser = authUserDao.selectById(userId);
        if (authUser == null) {
            throw new BusinessException(BusinessEnum.USER_NOT_FOUND);
        }
        checkUser(authUser);
        return OnlineUser.transfer(authUser);

    }

    private void checkUser(AuthUser authUser) {
        Integer status = authUser.getStatus();
        if (UserStatus.UNACTIVATED.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessEnum.USER_UNACTIVATED_ERROR);
        }
        if (UserStatus.LOCK.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessEnum.USER_LOCKED_ERROR);
        }
        if (UserStatus.FORBIDDEN.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessEnum.USER_FORBIDDEN_ERROR);
        }
        if (UserStatus.LOG_OFF.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessEnum.USER_LOG_OFF);
        }
    }

}
