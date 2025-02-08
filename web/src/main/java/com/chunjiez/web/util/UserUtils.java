package com.chunjiez.web.util;

import com.chunjiez.common.constant.BusinessCode;
import com.chunjiez.common.constant.UserStatus;
import com.chunjiez.common.entity.exception.authentication.AuthenticationException;
import com.chunjiez.database.entity.po.User;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/5
 */
public class UserUtils {

    public static void checkUser(User authUser) {
        Integer status = authUser.getStatus();
        if (UserStatus.UNACTIVATED.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessCode.USER_UNACTIVATED_ERROR);
        }
        if (UserStatus.LOCK.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessCode.USER_LOCKED_ERROR);
        }
        if (UserStatus.FORBIDDEN.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessCode.USER_FORBIDDEN_ERROR);
        }
        if (UserStatus.LOGOUT.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessCode.USER_CANCEL_ACCOUNT_ERROR);
        }
    }
}
