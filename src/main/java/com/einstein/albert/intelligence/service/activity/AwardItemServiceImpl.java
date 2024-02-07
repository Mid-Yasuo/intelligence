package com.einstein.albert.intelligence.service.activity;

import com.einstein.albert.intelligence.dao.AwardItemDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/9
 */
@Slf4j
@Service
public class AwardItemServiceImpl implements AwardItemService {

    private final AwardItemDao awardItemDao;

    public AwardItemServiceImpl(AwardItemDao awardItemDao) {
        this.awardItemDao = awardItemDao;
    }
}
