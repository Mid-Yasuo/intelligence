package com.einstein.intelligence.entity.vo.resp;

import com.einstein.intelligence.entity.po.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/9/26
 */
@Data
@Accessors(chain = true)
public class OnlineUser {

    private long userId;

    private String username;

    private String avatar;

    private String email;

    public static OnlineUser transfer(User authUser) {
        return new OnlineUser()
                .setUserId(authUser.getId())
                .setUsername(authUser.getUsername())
                .setAvatar("")
                .setEmail(authUser.getEmail());
    }
}
