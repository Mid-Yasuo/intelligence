package com.chunjiez.web.entity.vo.req.task;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
public class CreateTaskReq {

    @NotEmpty
    @Length(max = 50)
    private String name;

    @Length(max = 255)
    private String logo;

    @Length(max = 255)
    private String description;
}
