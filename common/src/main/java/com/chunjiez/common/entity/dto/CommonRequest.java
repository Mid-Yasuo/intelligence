package com.chunjiez.common.entity.dto;

import lombok.Data;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/9
 */
@Data
public class CommonRequest {

    private String url;

    private String method;

    private String requestHeaders;

    private String requestBody;

    private int httpStatus;

    private String contentType;

    private String responseHeaders;

    private String responseBody;
}
