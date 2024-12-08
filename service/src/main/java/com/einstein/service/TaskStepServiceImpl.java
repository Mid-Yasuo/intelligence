package com.einstein.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.TaskStepDao;
import com.einstein.database.entity.po.TaskStep;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务步骤表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Service
public class TaskStepServiceImpl extends ServiceImpl<TaskStepDao, TaskStep> implements TaskStepService {

}
