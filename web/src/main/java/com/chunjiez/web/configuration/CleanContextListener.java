package com.chunjiez.web.configuration;

import com.chunjiez.common.configuation.AuthContentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequestEvent;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Slf4j
@Component
public class CleanContextListener extends RequestContextListener {

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        log.debug("==========================>clean thread local<==========================");
        AuthContentHolder.cleanUserTokenCache();
    }
}
