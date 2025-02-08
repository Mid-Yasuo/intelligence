package com.chunjiez.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chunjiez.database.entity.po.Client;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface ClientDao extends BaseMapper<Client> {

}
