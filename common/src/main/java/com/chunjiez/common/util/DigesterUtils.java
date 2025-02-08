package com.chunjiez.common.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.nio.charset.StandardCharsets;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/9/25
 */
public class DigesterUtils {

    public static String md5(String text, String salt) {
        Digester digester = new Digester(DigestAlgorithm.MD5);
        digester.setSalt(salt.getBytes(StandardCharsets.UTF_8));
        return digester.digestHex(text);
    }

    public static boolean match(String text, String salt, String encode) {
        return md5(text, salt).equals(encode);
    }
}
