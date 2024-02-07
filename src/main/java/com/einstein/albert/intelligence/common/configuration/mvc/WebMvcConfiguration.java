package com.einstein.albert.intelligence.common.configuration.mvc;

import com.einstein.albert.intelligence.entity.constant.CommonConst;
import com.einstein.albert.intelligence.util.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/5
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<Filter> wrapperRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter((servletRequest, servletResponse, filterChain) -> {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String requestTraceId = request.getHeader(CommonConst.TRACE_ID);
            if (StringUtils.isBlank(requestTraceId)) {
                requestTraceId = RandomUtils.generateTraceId();
            }
            MDC.put(CommonConst.TRACE_ID, requestTraceId);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader(CommonConst.TRACE_ID, requestTraceId);
            String contentType = request.getContentType();
            if (StringUtils.isBlank(contentType) || contentType.contains(CommonConst.ContentType.JSON)) {
                HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
                filterChain.doFilter(requestWrapper, servletResponse);
            } else {
                filterChain.doFilter(request, response);
            }
        });
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("wrapper-filter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
