package com.dj.ego.shiro.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className UserRoleControl
 * @date 2019/9/5 8:48
 **/
@RequestMapping("/user-role")
@RestController
public class UserRoleControl extends BaseControl<UserRole> {

    @Autowired
    @Override
    protected void setService(IService<UserRole> service) {
        super.setService(service);
    }
}
