package com.chunjiez.common.util;

import com.chunjiez.common.entity.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/20
 */
@Slf4j
public class SpringExpressionUtils {

    private static final String SPEL_PREFIX = "#";

    private static final Map<String, String> BASIC_MAP = new ConcurrentHashMap<>();

    static {
        BASIC_MAP.put("byte", Byte.class.getName());
        BASIC_MAP.put("short", Short.class.getName());
        BASIC_MAP.put("int", Integer.class.getName());
        BASIC_MAP.put("long", Long.class.getName());
        BASIC_MAP.put("float", Float.class.getName());
        BASIC_MAP.put("double", Double.class.getName());
        BASIC_MAP.put("char", Character.class.getName());
        BASIC_MAP.put("boolean", Boolean.class.getName());
    }

    /**
     * SpEL(Spring Expression Language)表达式解析
     */
    private static final SpelExpressionParser SPEL_EXPRESSION_PARSER = new SpelExpressionParser();

    /**
     * 用于获取方法参数名字
     */
    private static final DefaultParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * <p>获取 SpEL 参数值</p>
     *
     * @param springExpression - [String]
     * @param method           - [Method]
     * @param args             - [Object]
     * @return String
     * @author 张春杰
     * @date 2024/1/6
     */
    public static String getValueBySpringExpression(String springExpression, Method method, Object[] args) {
        if (StringUtils.isBlank(springExpression)) {
            return "";
        }
        // 获取方法形参名数组
        String[] paramNames = NAME_DISCOVERER.getParameterNames(method);
        if (paramNames == null || paramNames.length < 1) {
            return "";
        }
        if (springExpression.contains(SPEL_PREFIX)) {
            checkExpression(springExpression);
            Expression expression = SPEL_EXPRESSION_PARSER.parseExpression(springExpression);
            // spring的表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 给上下文赋值
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            return Optional.ofNullable(expression.getValue(context)).orElse(springExpression).toString();
        }
        return springExpression;
    }

    /**
     * <p>SpEL 表达式校验</p>
     *
     * @param expression - [String] SpEL 表达式
     * @author 张春杰
     * @date 2024/1/6
     */
    public static void checkExpression(String expression) {
        try {
            ExpressionParser parser = new SpelExpressionParser();
            parser.parseExpression(expression, new TemplateParserContext());
        } catch (Exception exception) {
            log.error("SpEL expression parse error. {}", exception.getMessage());
            throw new SystemException(String.format("Invalid SpEL expression [%s]", expression));
        }
    }


    /**
     * <p>获取方法参数类型列表 将基本类转换为包装类 </p>
     *
     * @param method - [Method]
     * @return String
     * @author 张春杰
     * @date 2024/1/24
     */
    public static String[] getMethodParamTypes(Method method) {
        Class<?>[] classes = method.getParameterTypes();
        String[] classNames = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            classNames[i] = BASIC_MAP.getOrDefault(classes[i].getName(), classes[i].getName());
        }
        return classNames;
    }

}
