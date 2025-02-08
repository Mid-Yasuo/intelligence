package com.chunjiez.web.controller;

import com.chunjiez.common.util.RandomUtils;
import com.chunjiez.web.util.CaptchaUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@RestController
public class CommonController {

    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        CaptchaUtils.createCaptcha(RandomUtils.generateTraceId(), response);
    }
}
