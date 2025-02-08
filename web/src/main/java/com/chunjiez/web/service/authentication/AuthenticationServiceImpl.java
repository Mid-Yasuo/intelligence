package com.chunjiez.web.service.authentication;

import com.chunjiez.common.configuation.AuthContentHolder;
import com.chunjiez.common.constant.BooleanStatus;
import com.chunjiez.common.constant.BusinessCode;
import com.chunjiez.common.entity.TokenCache;
import com.chunjiez.common.entity.exception.authentication.AuthenticationException;
import com.chunjiez.common.entity.exception.authentication.UnauthorizedException;
import com.chunjiez.common.util.DigesterUtils;
import com.chunjiez.database.entity.po.User;
import com.chunjiez.database.entity.po.UserLoginLog;
import com.chunjiez.business.service.user.UserLoginLogService;
import com.chunjiez.business.service.user.UserService;
import com.chunjiez.web.entity.vo.req.LoginRequest;
import com.chunjiez.web.entity.vo.resp.OnlineUser;
import com.chunjiez.web.util.IpUtils;
import com.chunjiez.web.util.UserUtils;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final UserLoginLogService userLoginLogService;

    private final TokenService tokenService;

    @Override
    public String login(LoginRequest loginRequest) {
        UserLoginLog loginLog = UserLoginLog.build(loginRequest.getUsername(), IpUtils.getIp())
                .setLoginStatus(BooleanStatus.Y.getStatus());
        try {
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
        } catch (RuntimeException exception) {
            loginLog.setLoginStatus(BooleanStatus.N.getStatus()).setLoginErrorMessage(exception.getMessage());
            throw exception;
        } finally {
            userLoginLogService.save(loginLog);
        }
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
