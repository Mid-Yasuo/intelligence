package com.einstein.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.TaskStepLogDao;
import com.einstein.database.entity.po.TaskStepLog;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Service
public class TaskStepLogServiceImpl extends ServiceImpl<TaskStepLogDao, TaskStepLog> implements TaskStepLogService {

}
