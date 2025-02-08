package com.chunjiez.service.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunjiez.common.constant.TaskExecuteStatus;
import com.chunjiez.database.dao.TaskLogDao;
import com.chunjiez.database.entity.po.TaskLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 任务日志表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskLogServiceImpl extends ServiceImpl<TaskLogDao, TaskLog> implements TaskLogService {

    private final TaskLogDao taskLogDao;

    @Override
    public TaskLog createTaskExecuteLog(Long taskId) {
        TaskLog taskLog = new TaskLog()
                .setTaskId(taskId)
                .setStartTime(new Date())
                .setStatus(TaskExecuteStatus.IN_PROCESS.getStatus());
        taskLogDao.insert(taskLog);
        return taskLog;
    }

    @Override
    public TaskLog getTaskLog(Long taskId, Integer status) {
        return taskLogDao.selectOne(new LambdaQueryWrapper<TaskLog>().eq(TaskLog::getTaskId, taskId)
                .eq(Objects.nonNull(status), TaskLog::getStatus, status)
                .orderByDesc(TaskLog::getId)
                .last(" LIMIT 1"));
    }
}
