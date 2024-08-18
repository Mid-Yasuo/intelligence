package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.intelligence.dao.AutoDatabaseConfigDao;
import com.einstein.intelligence.entity.po.AutoDatabaseConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据库配置表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class AutoDatabaseConfigServiceImpl extends ServiceImpl<AutoDatabaseConfigDao, AutoDatabaseConfig> implements AutoDatabaseConfigService {

}
