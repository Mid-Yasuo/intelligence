package com.einstein.albert.intelligence.service;

import com.einstein.albert.intelligence.entity.po.Award;
import com.einstein.albert.intelligence.entity.vo.response.ActivityVO;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */

public interface LotteryService {

    /**
     * <p>doLotteryDraw</p>
     *
     * @param userId   - [Long]
     * @param activity - [ActivityVO]
     * @return Award
     * @author 张春杰
     * @date 2024/1/7
     */
    Award doLotteryDraw(Long userId, ActivityVO activity);
}
