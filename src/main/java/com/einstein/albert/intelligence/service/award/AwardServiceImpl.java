package com.einstein.albert.intelligence.service.award;

import com.einstein.albert.intelligence.dao.AwardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/9
 */
@Slf4j
@Service
public class AwardServiceImpl implements AwardService {

    private final AwardDao awardDao;

    public AwardServiceImpl(AwardDao awardDao) {
        this.awardDao = awardDao;
    }
}
