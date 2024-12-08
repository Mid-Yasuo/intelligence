package com.einstein.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/9/18
 */
@Data
@Accessors(chain = true)
public class TokenCache {

    private long userId;

    private String username;

    private String token;

    private String loginIp;

    private String userAgent;

    private String loginTime;

    private String clientId;

    private List<String> clientChildren;
}
