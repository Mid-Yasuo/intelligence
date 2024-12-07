package com.einstein.web.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/6/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResult<D> extends Result<D> {

    private long totalCount;

    private long pageCount;

    private long pageNo;

    private long pageSize;

    public PageResult(long totalCount, long pageNo, long pageSize, D d) {
        this.setCode(200);
        this.setMsg("操作成功");
        this.totalCount = totalCount;
        this.pageCount = totalCount / pageSize;
        long remainder = totalCount % pageSize;
        if (remainder > 0) {
            this.pageCount++;
        }
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.setData(d);
    }

}
