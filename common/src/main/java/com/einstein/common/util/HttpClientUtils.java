package com.einstein.common.util;

import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestResponse;
import com.einstein.common.constant.HttpMethod;
import com.einstein.common.entity.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Map;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Slf4j
public class HttpClientUtils {

    public static ForestResponse<?> get(String url, Map<String, String> headers) {
        return Forest.get(url).addHeader(headers).executeAsResponse();
    }

    public static ForestResponse<?> post(String url, Map<String, String> headers, Object body) {
        return Forest.get(url).addHeader(headers).contentTypeJson().addBody(body).executeAsResponse();
    }

    public static ForestResponse<?> request(HttpMethod method, String url, Map<String, String> headers, Object body) {
        if (HttpMethod.GET.equals(method)) {
            return get(url, headers);
        } else if (HttpMethod.POST.equals(method)) {
            return post(url, headers, body);
        } else {
            throw new SystemException("http method not support");
        }
    }

    public static void download(String filename, String url, Map<String, String> headers) {
        try {
            ForestResponse<?> forestResponse = Forest.get(url).addHeader(headers).executeAsResponse();
            InputStream inputStream = forestResponse.getInputStream();
            FileUtils.write(filename, inputStream);
        } catch (Exception exception) {
            log.info("download file error", exception);
        }
    }
}
