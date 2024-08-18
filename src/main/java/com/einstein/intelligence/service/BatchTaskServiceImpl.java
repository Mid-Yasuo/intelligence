package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.intelligence.dao.BatchTaskDao;
import com.einstein.intelligence.entity.po.BatchTask;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 批量任务表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class BatchTaskServiceImpl extends ServiceImpl<BatchTaskDao, BatchTask> implements BatchTaskService {

}
