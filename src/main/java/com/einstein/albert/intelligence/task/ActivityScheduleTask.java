package com.einstein.albert.intelligence.task;

import com.einstein.albert.intelligence.entity.annotation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Slf4j
@Component
public class ActivityScheduleTask {

    private TaskJob taskJob;

    @Autowired

    public void setTaskJob(TaskJob taskJob) {
        this.taskJob = taskJob;
    }


    @Async("taskThreadPool")
    @Scheduled(cron = "*/5 * * * *  ?")
    @DistributedLock(keyPrefix = "freshActivityStatus")
    public void freshActivityStatus() {
        int pageNo = 1;
        int pageSize = 100;
        while (true) {
            boolean batch = taskJob.batchRefresh(pageNo, pageSize);
            if (batch) {
                pageNo++;
                continue;
            }
            break;
        }
    }

}
