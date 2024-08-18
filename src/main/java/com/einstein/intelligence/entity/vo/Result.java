package com.einstein.intelligence.entity.vo;

import com.einstein.intelligence.entity.constant.BusinessEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/6/26
 */
@Data
public class Result<D> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private D data;

    public static <D> Result<D> success() {
        Result<D> result = new Result<>();
        result.code = 200;
        result.msg = "操作成功";
        return result;
    }

    public static <D> Result<D> success(D d) {
        Result<D> result = new Result<>();
        result.code = 200;
        result.msg = "操作成功";
        result.data = d;
        return result;
    }

    public static <D> Result<D> successWithMsg(String message, D data) {
        Result<D> result = new Result<>();
        result.code = 200;
        result.msg = message;
        result.data = data;
        return result;
    }

    public static <D> Result<D> fail(int code, String msg) {
        Result<D> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <D> Result<D> fail(BusinessEnum bizEnum) {
        Result<D> result = new Result<>();
        result.code = bizEnum.getCode();
        result.msg = bizEnum.getMessage();
        return result;
    }
}
