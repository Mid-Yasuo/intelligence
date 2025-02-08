package com.chunjiez.database.configuration.mybatis;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.chunjiez.common.configuation.AuthContentHolder;
import com.chunjiez.common.constant.Constant;
import com.chunjiez.common.entity.TokenCache;
import com.chunjiez.common.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Objects;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/6
 */
@Slf4j
public class MybatisDataPermissionInterceptor extends JsqlParserSupport implements InnerInterceptor, IgnoreDataPermissionHandler {


    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
                            BoundSql boundSql) {
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(parserSingle(mpBs.sql(), ms.getId()));
    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        SelectBody selectBody = select.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            this.setWhere((PlainSelect) selectBody, (String) obj);
        } else if (selectBody instanceof SetOperationList) {
            SetOperationList setOperationList = (SetOperationList) selectBody;
            List<SelectBody> selectBodyList = setOperationList.getSelects();
            selectBodyList.forEach(s -> this.setWhere((PlainSelect) s, (String) obj));
        }
        String selectSql = select.toString();
        System.out.println("selectSql = " + selectSql);
    }

    /**
     * 设置 where 条件
     *
     * @param plainSelect  查询对象
     * @param whereSegment 查询条件片段
     */
    protected void setWhere(PlainSelect plainSelect, String whereSegment) {
        Table table = (Table) plainSelect.getFromItem();
        String tableName = table.getName();
        if (ignoreTables().contains(tableName)) {
            log.info("表：{} 跳过数据权限", tableName);
            return;
        }
        Alias alias = table.getAlias();
        if (Objects.nonNull(alias)) {
            tableName = alias.getName();
        }
        TokenCache userTokenCache = AuthContentHolder.getUserTokenCache();
        if (Objects.isNull(userTokenCache)) {
            return;
        }
        List<String> clientChildren = userTokenCache.getClientChildren();
        String clientIds = CollectionUtils.join(clientChildren, ",");
        String segment = tableName + "." + Constant.CLIENT_ID + " in(" + clientIds + ")";
        try {
            Expression expression = CCJSqlParserUtil.parseCondExpression(segment);
            Expression where = plainSelect.getWhere();
            if (Objects.nonNull(where)) {
                plainSelect.setWhere(new AndExpression(where, expression));
            }
        } catch (JSQLParserException exception) {
            exception.printStackTrace();
        }
    }
}
