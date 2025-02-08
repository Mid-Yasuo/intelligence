package com.chunjiez.common.entity.dto;

import lombok.Data;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
public class TaskStepResponse {

    private boolean success;

    private Integer httpStatus;

    private Byte[] byteData;

    private String data;
}
