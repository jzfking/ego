package com.dj.ego;

import com.dj.ego.common.data.DataCheckUtil;
import com.dj.ego.shiro.config.ShiroPermissionConfig;
import com.dj.ego.shiro.entity.po.User;
import com.dj.ego.shiro.service.crud.UserServiceImpl;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EgoApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void data() {
        User user = userService.selectOneById(1);
        System.out.println(DataCheckUtil.hasOtherProperties(user));
    }

    @Test
    public void shiro() {
        WildcardPermission wildcardPermission1 = new WildcardPermission("user:read:ids");
        WildcardPermission wildcardPermission2 = new WildcardPermission("user:read:id");
        System.out.println(wildcardPermission1.implies(wildcardPermission2));
    }

    @Test
    public void collection() {
        System.out.println(new HashSet<>(new ArrayList<>()));
    }

    @Autowired
    ShiroPermissionConfig config;

    @Test
    public void config(){
//        config.getUrlPermsMap();
    }

}
