/*
 * Copyright (c) 2023.  All rights reserved.
 */

package com.einstein.albert.intelligence.util;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arthur Zhang
 * @version 1.0.0
 * @date 2023/3/3
 */
@Slf4j
public class CaptchaUtils {

    private static final String CAPTCHA_PREFIX = "captcha:";

    public static void createCaptcha(String captchaId, HttpServletResponse response) throws IOException {
        SpecCaptcha captcha = new SpecCaptcha(100, 40, 4);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        String code = captcha.text();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setContentType("image/gif");
        response.setHeader("captcha-id", captchaId);
        RedisUtils.set(CAPTCHA_PREFIX + captchaId, code, 300);
        captcha.out(response.getOutputStream());
    }

    public boolean validateCaptcha(String captchaId, String code) {
        if (StringUtils.isNotBlank(code)) {
            String cacheCode = (String) RedisUtils.getValue(CAPTCHA_PREFIX + captchaId);
            if (StringUtils.isNotBlank(cacheCode)) {
                RedisUtils.deleteKey(CAPTCHA_PREFIX + captchaId);
                return code.equals(cacheCode);
            }
        }
        return false;
    }
}
