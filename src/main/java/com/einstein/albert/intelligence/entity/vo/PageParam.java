package com.einstein.albert.intelligence.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Data
@Accessors(chain = true)
public class PageParam {

    private Integer pageNo;

    private Integer pageSize;

    private Integer total;
}
