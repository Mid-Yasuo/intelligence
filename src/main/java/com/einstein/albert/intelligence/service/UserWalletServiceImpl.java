package com.einstein.albert.intelligence.service;

import com.einstein.albert.intelligence.dao.UserWalletDao;
import com.einstein.albert.intelligence.entity.constant.UserWalletStatus;
import com.einstein.albert.intelligence.entity.po.UserWallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Slf4j
@Service
public class UserWalletServiceImpl implements UserWalletService {

    private final UserWalletDao userWalletDao;

    public UserWalletServiceImpl(UserWalletDao userWalletDao) {
        this.userWalletDao = userWalletDao;
    }

    @Override
    public UserWallet getUserWallet(Long userId) {
        UserWallet userWallet = userWalletDao.selectByUserId(userId);
        if (userWallet == null) {
            userWallet = new UserWallet().setUserId(userId)
                    .setTotalAmount(BigDecimal.ZERO)
                    .setBalanceAmount(BigDecimal.ZERO)
                    .setIntegral(0L)
                    .setStatus(UserWalletStatus.NORMAL.getValue());
            userWalletDao.insert(userWallet);
        }
        return userWallet;
    }
}
