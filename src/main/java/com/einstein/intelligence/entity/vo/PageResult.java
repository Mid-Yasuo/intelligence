package com.einstein.intelligence.entity.vo;

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

    private int totalCount;

    private int pageCount;

    private int pageNo;

    private int pageSize;

    public PageResult(int totalCount, int pageNo, int pageSize, D d) {
        this.setCode(200);
        this.setMsg("操作成功");
        this.totalCount = totalCount;
        this.pageCount = totalCount / pageSize;
        int remainder = totalCount % pageSize;
        if (remainder > 0) {
            this.pageCount++;
        }
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.setData(d);
    }
}
