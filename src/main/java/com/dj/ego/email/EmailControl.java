package com.dj.ego.email;

import com.dj.ego.shiro.service.EmailCodeManager;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

/**
 * @author 戴俊明
 * @className EmailControl
 * @description 邮件控制层
 * @date 2019/8/20 15:49
 **/
@Api("邮件验证")
@RestController
@RequestMapping("/email")
public class EmailControl {

    @Autowired
    private EmailService service;
    @Autowired
    private EmailCodeManager manager;

    @PostMapping("/{id}")
    public ResponseEntity sendMail(@PathVariable Integer id, @RequestBody Email email) {
        String code = service.newCode();
        manager.setCode(id, code);
        email.setTitle("账户验证");
        email.setContent(service.newHtmlTemplate(code));
        try {
            service.sendHtmlMail(email);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (MailException e) {
            e.printStackTrace();
            manager.remove(id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
