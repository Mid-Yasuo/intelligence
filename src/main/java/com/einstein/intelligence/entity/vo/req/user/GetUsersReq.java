package com.einstein.intelligence.entity.vo.req.user;

import com.einstein.intelligence.entity.vo.PageParam;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/11
 */
@Data
public class GetUsersReq {


    private String username;

    @NotNull
    @Valid
    private PageParam pageParam;
}
