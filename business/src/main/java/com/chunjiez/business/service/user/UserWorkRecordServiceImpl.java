package com.chunjiez.business.service.user;

import com.chunjiez.database.entity.po.UserWorkRecord;
import com.chunjiez.database.dao.UserWorkRecordDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户打卡记录表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2025-02-08
 */
@Service
public class UserWorkRecordServiceImpl extends ServiceImpl<UserWorkRecordDao, UserWorkRecord> implements UserWorkRecordService {

}
