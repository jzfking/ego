package com.dj.ego.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.entity.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className OrderServiceImpl
 * @description TODO
 * @date 2019/9/5 22:56
 **/
@Service
public class OrderServiceImpl extends BaseService<Order> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Order> dao) {
        super.setDao(dao);
    }
}
