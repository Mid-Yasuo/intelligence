package com.einstein.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.TaskScriptDao;
import com.einstein.database.entity.po.TaskScript;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务脚本表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Service
public class TaskScriptServiceImpl extends ServiceImpl<TaskScriptDao, TaskScript> implements TaskScriptService {

}
