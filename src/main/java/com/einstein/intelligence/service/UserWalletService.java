package com.einstein.intelligence.service;

import com.einstein.intelligence.entity.po.UserWallet;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
public interface UserWalletService {

    /**
     * <p>获取用户钱包</p>
     *
     * @param userId - [Long]
     * @return UserWallet
     * @author 张春杰
     * @date 2024/1/7
     */
    UserWallet getUserWallet(Long userId);
}
