package com.chunjiez.database.configuration.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.chunjiez.common.configuation.AuthContentHolder;
import com.chunjiez.common.entity.TokenCache;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/7/3
 */
@MapperScan(basePackages = "com.chunjiez.database.dao")
@Configuration
public class MyBatisConfiguration implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.strictInsertFill(metaObject, "createTime", Date.class, date);
        this.strictInsertFill(metaObject, "updateTime", Date.class, date);
        TokenCache tokenCache = AuthContentHolder.getUserTokenCache();
        if (Objects.nonNull(tokenCache)) {
            this.strictInsertFill(metaObject, "createBy", Long.class, tokenCache.getUserId());
            this.strictInsertFill(metaObject, "updateBy", Long.class, tokenCache.getUserId());
            this.strictInsertFill(metaObject, "clientId", String.class, tokenCache.getClientId());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        this.strictUpdateFill(metaObject, "updateTime", Date.class, date);
        TokenCache tokenCache = AuthContentHolder.getUserTokenCache();
        if (Objects.nonNull(tokenCache)) {
            this.strictInsertFill(metaObject, "updateBy", Long.class, tokenCache.getUserId());
        }
    }

    /**
     * <p>强制更新自动填充字段</p>
     *
     * @param metaObject - [MetaObject]
     * @param fieldName  - [String]
     * @param fieldVal   - [Supplier<?>]
     * @return
     * @author 张春杰
     * @date 2023/2/3
     */
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }

    /**
     * 新的分页插件,一缓和二缓遵循 mybatis 的规则
     * 乐观锁需要在字段上加@Version注解 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 整数类型下 newVersion = oldVersion + 1
     * newVersion 会回写到 entity 中
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(100L);
        //分页拦截
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        //乐观锁拦截
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new MybatisDataPermissionInterceptor());
        return interceptor;
    }
}
