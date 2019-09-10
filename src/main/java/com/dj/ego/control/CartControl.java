package com.dj.ego.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.entity.po.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className CartControl
 * @description TODO
 * @date 2019/9/5 22:59
 **/
@RequestMapping("/cart")
@RestController
public class CartControl extends BaseControl<Cart> {

    @Autowired
    @Override
    protected void setService(IService<Cart> service) {
        super.setService(service);
    }
}
