package com.einstein.albert.intelligence.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author ZhangChunjie
 * @Date 2022/12/5
 */
public class IoUtils {

    public static final int EOF = -1;

    private IoUtils() {
        //空参构造
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int length;
        while ((length = inputStream.read(bytes)) != EOF) {
            out.write(bytes, 0, length);
        }
        return out.toByteArray();
    }

    public static String read(InputStream inputStream, Charset charset) throws IOException {
        byte[] readBytes = readBytes(inputStream);
        return new String(readBytes, charset);
    }

}
