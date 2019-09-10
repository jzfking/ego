package com.dj.ego.shiro.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.RoleAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className RoleAccessControl
 * @date 2019/9/5 8:49
 **/
@RequestMapping("/role-access")
@RestController
public class RoleAccessControl extends BaseControl<RoleAccess> {

    @Autowired
    @Override
    protected void setService(IService<RoleAccess> service) {
        super.setService(service);
    }
}
