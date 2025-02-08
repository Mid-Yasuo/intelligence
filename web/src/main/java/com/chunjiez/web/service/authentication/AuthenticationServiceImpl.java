package com.chunjiez.web.service.authentication;

import com.chunjiez.common.configuation.AuthContentHolder;
import com.chunjiez.common.constant.BusinessCode;
import com.chunjiez.common.entity.TokenCache;
import com.chunjiez.common.entity.exception.authentication.AuthenticationException;
import com.chunjiez.common.entity.exception.authentication.UnauthorizedException;
import com.chunjiez.common.util.DigesterUtils;
import com.chunjiez.database.entity.po.User;
import com.chunjiez.service.user.UserService;
import com.chunjiez.web.entity.vo.req.LoginRequest;
import com.chunjiez.web.entity.vo.resp.OnlineUser;
import com.chunjiez.web.util.UserUtils;
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

    private UserService userService;

    private TokenService tokenService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User authUser = userService.getByUsername(loginRequest.getUsername());
        if (authUser == null) {
            throw new AuthenticationException(loginRequest.getUsername(), BusinessCode.USER_PASSWORD_ERROR);
        }
        UserUtils.checkUser(authUser);
        String password = authUser.getPassword();
        boolean match = DigesterUtils.match(loginRequest.getPassword(), "", password);
        if (BooleanUtils.isFalse(match)) {
            throw new AuthenticationException(loginRequest.getUsername(), BusinessCode.USER_PASSWORD_ERROR);
        }
        return tokenService.handleInitCacheToken(authUser);
    }

    @Override
    public OnlineUser getCurrentUser() {
        TokenCache tokenCache = AuthContentHolder.getUserTokenCache();
        if (tokenCache == null) {
            throw new UnauthorizedException(BusinessCode.USER_NOT_LOGIN);
        }
        long userId = tokenCache.getUserId();
        User authUser = userService.getById(userId);
        UserUtils.checkUser(authUser);
        return OnlineUser.transfer(authUser);
    }

}
