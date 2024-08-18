package com.einstein.intelligence.common.configuration.mvc;

import com.einstein.intelligence.util.IoUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 重写 getInputStream() 方法，使请求体可被多次读取
 *
 * @author 张春杰
 * @date 2021/11/19
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     */
    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = IoUtils.readBytes(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedServletInputStream(body);
    }

    public static class CachedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream inputStream;

        public CachedServletInputStream(byte[] bytes) {
            this.inputStream = new ByteArrayInputStream(bytes);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() {
            return inputStream.read();
        }
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}
