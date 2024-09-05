package com.einstein.intelligence.common;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/5
 */
public class Constant {

    public static final String AUTH_HEAD = "Authorization";

    public static final String REQUEST_ID = "RequestId";

    public static final String VERSION = "Version";

    public static final String TRACE_ID = "TRACE_ID";
    public static final String USER_TOKEN_CACHE = "USER_TOKEN:";
    public static final String USER_CACHE = "USER:";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final class ContentType {
        public static final String JSON = "application/json";
    }

    public static class Status {
        public static final int N = 0;

        public static final int Y = 1;
    }
}
