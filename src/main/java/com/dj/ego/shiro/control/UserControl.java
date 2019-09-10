package com.dj.ego.shiro.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className UserControl
 * @date 2019/9/5 8:45
 **/
@RequestMapping("/user")
@RestController
public class UserControl extends BaseControl<User> {

    @Autowired
    @Override
    protected void setService(IService<User> service) {
        super.setService(service);
    }
}
