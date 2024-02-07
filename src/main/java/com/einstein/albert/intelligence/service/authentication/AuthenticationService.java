package com.einstein.albert.intelligence.service.authentication;

import com.einstein.albert.intelligence.entity.vo.request.LoginRequest;
import com.einstein.albert.intelligence.entity.vo.response.OnlineUser;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
public interface AuthenticationService {

    /**
     * <p>用户登录</p>
     *
     * @param loginRequest - [LoginRequest]
     * @return String
     * @author 张春杰
     * @date 2024/1/6
     */
    String login(LoginRequest loginRequest);

    /**
     * <p>获取当前用户信息</p>
     *
     * @return OnlineUser
     * @author 张春杰
     * @date 2024/1/7
     */
    OnlineUser getCurrentUser();
}
