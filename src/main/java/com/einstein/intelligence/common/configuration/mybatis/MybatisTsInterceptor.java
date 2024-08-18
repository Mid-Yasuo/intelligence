package com.einstein.intelligence.common.configuration.mybatis;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

/**
 * <code>@Signature注解参数说明
 * type 四个可拦截类的class对象；
 * Executor sql的内部执行器
 * ParameterHandler 拦截参数的处理
 * StatementHandler 拦截sql的构建
 * ResultSetHandler 拦截结果的处理
 * 拦截顺序为从上到下的顺序为：Executor -> ParameterHandler -> StatementHandler -> ResultSetHandler
 * method 类中的方法名称，入上述的prepare就是StatementHandler中的prepare方法；
 * args 方法的形参类型，数量类型与拦截类中的方法声明一致；</code>
 *
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/23
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})
})
public class MybatisTsInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long execute = System.currentTimeMillis() - start;
        Object param = null;
        if (args.length > 1) {
            param = args[1];
        }
        String mappedStatementId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(param);
        String showSql = showSql(mappedStatement.getConfiguration(), boundSql);
        String template = "\n\n============================  Sql Start  ============================" +
                "\nMappedStatement ID  ：{}" +
                "\nExecute SQL         ：{}" +
                "\nExecute Time        ：{} ms" +
                "\n============================  Sql  End   ============================\n";
        log.info(template, mappedStatementId, showSql, execute);
        return proceed;
    }


    /**
     * 对sql中的?进行参数替换
     *
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        // 获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // sql语句中多个空格都用一个空格代替
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
            // 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换　　　　　　
            // 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

            } else {
                // MetaObject主要是封装了original Object对象，提供了get和set的方法用于获取和设置original Object的属性值
                // 主要支持对JavaBean、Collection、Map三种类型对象的操作
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 该分支是动态sql
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));

                    } else {
                        //打印出缺失，提醒该参数缺失并防止错位
                        sql = sql.replaceFirst("\\?", "missing");
                    }
                }
            }
        }
        return sql;
    }

    /**
     * <p>如果参数是String，则添加单引号;
     * 如果是日期，则转换为时间格式器并加单引号;</p>
     *
     * @param obj - [Object]
     * @return String
     * @author 张春杰
     * @date 2024/1/24
     */
    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}
