package com.einstein.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.intelligence.entity.po.UserWallet;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户钱包表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Mapper
public interface UserWalletDao extends BaseMapper<UserWallet> {

    UserWallet selectByUserId(Long userId);
}
