package com.einstein.web.util;

import com.einstein.common.constant.BusinessEnum;
import com.einstein.common.constant.UserStatus;
import com.einstein.common.entity.exception.authentication.AuthenticationException;
import com.einstein.database.entity.po.User;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/5
 */
public class UserUtils {

    public static void checkUser(User authUser) {
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
        if (UserStatus.LOGOUT.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessEnum.USER_CANCEL_ACCOUNT_ERROR);
        }
    }
}
