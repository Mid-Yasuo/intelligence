package com.einstein.web.entity.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public <T> IPage<T> toMybatisPage() {
        return new Page<>(pageNo, pageSize);
    }
}
