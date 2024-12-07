package com.einstein.web.entity.vo.req;

import lombok.Data;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/9/18
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}
