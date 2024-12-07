package com.einstein.web.service;

import com.einstein.common.configuation.AuthContentHolder;
import com.einstein.common.constant.BusinessEnum;
import com.einstein.common.entity.TokenCache;
import com.einstein.common.entity.exception.authentication.AuthenticationException;
import com.einstein.common.entity.exception.authentication.UnauthorizedException;
import com.einstein.common.util.DigesterUtils;
import com.einstein.database.dao.UserDao;
import com.einstein.database.entity.po.User;
import com.einstein.web.configuration.TokenCacheClient;
import com.einstein.web.entity.vo.req.LoginRequest;
import com.einstein.web.entity.vo.resp.OnlineUser;
import com.einstein.web.util.UserUtils;
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

    private UserDao userDao;

    private TokenCacheClient tokenCacheClient;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setTokenCacheClient(TokenCacheClient tokenCacheClient) {
        this.tokenCacheClient = tokenCacheClient;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User authUser = userDao.selectByUsername(loginRequest.getUsername());
        if (authUser == null) {
            throw new AuthenticationException(loginRequest.getUsername(), BusinessEnum.USER_PASSWORD_ERROR);
        }
        UserUtils.checkUser(authUser);
        String password = authUser.getPassword();
        boolean match = DigesterUtils.match(loginRequest.getPassword(), "", password);
        if (BooleanUtils.isFalse(match)) {
            throw new AuthenticationException(loginRequest.getUsername(), BusinessEnum.USER_PASSWORD_ERROR);
        }
        return tokenCacheClient.handleInitCacheToken(authUser);
    }

    @Override
    public OnlineUser getCurrentUser() {
        TokenCache tokenCache = AuthContentHolder.getUserTokenCache();
        if (tokenCache == null) {
            throw new UnauthorizedException(BusinessEnum.USER_NOT_LOGIN);
        }
        long userId = tokenCache.getUserId();
        User authUser = userDao.selectById(userId);
        UserUtils.checkUser(authUser);
        return OnlineUser.transfer(authUser);
    }

}
