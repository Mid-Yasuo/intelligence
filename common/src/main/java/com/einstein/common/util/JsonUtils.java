package com.einstein.common.util;

import com.einstein.common.entity.exception.JsonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/4/11
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final String LEFT_BRACE = "{";
    public static final String RIGHT_BRACE = "}";
    public static final String LEFT_BRACKET = "[";
    public static final String RIGHT_BRACKET = "]";

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //是否允许字段注释
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        OBJECT_MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);

    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            throw new JsonException(exception.getMessage());
        }
    }

    public static <T> T toJavaBean(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException exception) {
            throw new JsonException(exception.getMessage());
        }
    }

    public static <T> T toJavaBean(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonException(exception.getMessage());
        }
    }

    public static <T> T toJavaBean(InputStream inputStream, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, typeReference);
        } catch (IOException exception) {
            throw new JsonException(exception.getMessage());
        }
    }


    /**
     * <p>过滤引起Log Forging漏洞的敏感字符的公共方法</p>
     *
     * @param log - [String]
     * @return String
     * @author 张春杰
     * @date 2023/2/24
     */
    public static String convert(String log) {
        List<String> dangers = Arrays.asList("%0d", "%0D", "\r", "%0a", "%0D", "\n");
        String normalize = Normalizer.normalize(log, Normalizer.Form.NFKC);
        for (String danger : dangers) {
            normalize = normalize.replace(danger, "");
        }
        return normalize;
    }

    public static boolean isJson(String json) {
        if (json.startsWith(LEFT_BRACE) && json.endsWith(RIGHT_BRACE)) {
            return true;
        }
        return json.startsWith(LEFT_BRACKET) && json.endsWith(RIGHT_BRACKET);
    }

    public static BigDecimal formatNumber(String num, int scale) {
        if (StringUtils.isBlank(num)) {
            num = "0.00";
        }
        return new BigDecimal(num).setScale(scale, RoundingMode.HALF_UP);
    }

    public static String calculatePercentage(String rate) {
        if (StringUtils.isBlank(rate)) {
            return "0.00";
        }
        return new BigDecimal(rate).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

}
