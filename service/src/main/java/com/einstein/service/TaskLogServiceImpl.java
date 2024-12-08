package com.einstein.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.TaskLogDao;
import com.einstein.database.entity.po.TaskLog;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务日志表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogDao, TaskLog> implements TaskLogService {

}
