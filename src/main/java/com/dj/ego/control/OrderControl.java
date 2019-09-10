package com.dj.ego.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.entity.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className OrderControl
 * @description TODO
 * @date 2019/9/5 23:02
 **/
@RequestMapping("/order")
@RestController
public class OrderControl extends BaseControl<Order> {

    @Autowired
    @Override
    protected void setService(IService<Order> service) {
        super.setService(service);
    }
}
