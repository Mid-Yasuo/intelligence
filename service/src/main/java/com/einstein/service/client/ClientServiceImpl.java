package com.einstein.service.client;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.ClientDao;
import com.einstein.database.entity.po.Client;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements ClientService {

}
