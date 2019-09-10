package com.dj.ego.shiro.control;

import cn.hutool.crypto.digest.MD5;
import com.dj.ego.common.http.HttpBodyEntity;
import com.dj.ego.shiro.entity.dto.UserAuthenticationInfo;
import com.dj.ego.shiro.entity.po.User;
import com.dj.ego.shiro.service.AuthUserAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 戴俊明
 * @className AuthControl
 * @description 用户账户控制
 * @date 2019/8/23 16:45
 **/

@RequestMapping("/auth")
@RestController
public class AuthUserAccountControl {

    @Autowired
    private AuthUserAccountService service;
    @Autowired
    private MD5 md5;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserAuthenticationInfo userInfo) {
        userInfo.setPassword(md5.reset().setSalt(userInfo.getUsername().getBytes(StandardCharsets.UTF_8))
                .setDigestCount(2).digestHex(userInfo.getPassword(), StandardCharsets.UTF_8));
        return ResponseEntity.ok().header("token", service.login(userInfo)).build();
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipals() != null) {
            System.out.println(subject.getPrincipals().getPrimaryPrincipal());
        }
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        user.setPassword(md5.reset().setSalt(user.getUsername().getBytes(StandardCharsets.UTF_8))
                .setDigestCount(2).digestHex(user.getPassword(), StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(HttpBodyEntity.builder().data(service.register(user)).build());
    }

    @DeleteMapping("/cancel/id/{id}")
    public ResponseEntity cancelRegister(@PathVariable Integer id) {
        service.cancelRegister(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/active/id/{id}")
    public ResponseEntity activeUser(@PathVariable Integer id) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.activeUser(id)).build());
    }

    @PostMapping("/hasPermissions")
    public ResponseEntity hasPermissions(@RequestBody List<String> list) {
        return ResponseEntity.ok().body(service.hasPermissions(list));
    }

    @GetMapping("/401")
    public ResponseEntity unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/403")
    public ResponseEntity forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
