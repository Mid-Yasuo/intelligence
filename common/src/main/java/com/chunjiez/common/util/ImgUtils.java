package com.chunjiez.common.util;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/8/7
 */
@Slf4j
public class ImgUtils {

    /**
     * <p>压缩图片</p>
     *
     * @param imageBase64 - [String]
     * @param minWidth    - [int]
     * @param minHeight   - [int]
     * @param targetSize  - [long]
     * @return String
     * @author 张春杰
     * @date 2024/1/5
     */
    public static String compress(String imageBase64, int minWidth, int minHeight, long targetSize) {
        ByteArrayOutputStream outputStream = null;
        try {
            byte[] bytes = Base64.decode(imageBase64);
            BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(bytes));
            // 计算输入图片的当前文件大小
            long currentFileSize = bytes.length;
            // 如果输入图片的文件大小已经小于等于目标文件大小，无需压缩
            if (currentFileSize <= targetSize) {
                System.out.println("输入图片的文件大小已经小于等于目标文件大小，无需压缩。");
                return imageBase64;
            }
            // 计算压缩比例
            double compressionRatio = (double) targetSize / currentFileSize;
            // 计算输出图片的宽度和高度
            int width = (int) (inputImage.getWidth() * Math.sqrt(compressionRatio));
            if (width < minWidth) {
                width = minWidth;
            }
            int height = (int) (inputImage.getHeight() * Math.sqrt(compressionRatio));
            if (height < minHeight) {
                height = minHeight;
            }
            // 使用Java内置图像处理方法进行图像压缩
            BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
            outputImage.getGraphics().drawImage(inputImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            // 将输出图像写入文件
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(outputImage, "jpg", outputStream);
            log.info("成功将图片压缩至指定文件大小。");
            return Base64.encode(outputStream.toByteArray());
        } catch (IOException exception) {
            log.error("image compress failed.", exception);
            throw new RuntimeException(exception);
        } finally {
            if (Objects.nonNull(outputStream)) {
                try {
                    outputStream.close();
                } catch (IOException exception) {
                    log.error("关闭流异常", exception);
                }
            }
        }
    }
}
