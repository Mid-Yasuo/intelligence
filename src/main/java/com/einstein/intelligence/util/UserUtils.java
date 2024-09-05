package com.einstein.intelligence.util;

import com.einstein.intelligence.entity.constant.BusinessEnum;
import com.einstein.intelligence.entity.constant.UserStatus;
import com.einstein.intelligence.entity.po.User;
import com.einstein.intelligence.exception.authentication.AuthenticationException;

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
        if (UserStatus.LOG_OFF.getCode() == status) {
            throw new AuthenticationException(authUser.getUsername(), BusinessEnum.USER_CANCEL_ACCOUNT_ERROR);
        }
    }
}
