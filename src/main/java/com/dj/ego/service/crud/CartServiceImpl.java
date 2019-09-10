package com.dj.ego.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.entity.po.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className CartServiceImpl
 * @description TODO
 * @date 2019/9/5 22:54
 **/
@Service
public class CartServiceImpl extends BaseService<Cart> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Cart> dao) {
        super.setDao(dao);
    }
}
