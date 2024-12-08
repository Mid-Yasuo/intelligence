package com.einstein.common.util;

import com.einstein.common.constant.Constant;
import com.einstein.common.entity.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/2/3
 */
@Slf4j
public class FileUtils {

    private static final String BASE_DIR = "Download/";

    static {
        File file = new File(BASE_DIR);
        boolean mkdirs = file.mkdirs();
        log.info("mkdirs:{}==>result:{}", BASE_DIR, mkdirs || file.exists());
    }

    public static void mkdirs(String filePath) {
        int index = filePath.lastIndexOf(Constant.FILE_SEPARATOR);
        if (index < 0) {
            return;
        }
        File file = new File(filePath.substring(0, index));
        boolean mkdirs = file.mkdirs();
        log.info("mkdirs:{}==>result:{}", filePath, mkdirs || file.exists());
    }

    public static String write(String filePath, InputStream inputStream) {
        filePath = BASE_DIR + filePath;
        mkdirs(filePath);
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytes = new byte[1024 * 4];
            int length;
            while ((length = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, length);
                outputStream.flush();
            }
            return filePath;
        } catch (IOException exception) {
            log.error("write file [{{}] error", filePath, exception);
            throw new SystemException("write file error");
        }
    }
}
