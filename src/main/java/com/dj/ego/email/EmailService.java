package com.dj.ego.email;

import com.dj.ego.common.GlobalException;
import com.dj.ego.common.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author 戴俊明
 * @className EmailService
 * @description 邮件业务
 * @date 2019/8/20 14:29
 **/
@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Deprecated
    public void sendTextMail(Email mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(mail.getReceiver());
        mailMessage.setSubject(mail.getTitle());
        mailMessage.setText(mail.getContent());
        mailMessage.setSentDate(new Date());
        sender.send(mailMessage);
    }

    public void sendHtmlMail(Email email) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(), true);
        } catch (MessagingException e) {
            throw GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .serviceStatus(ServiceStatus.mail_can_not_init).build();
        }
        sender.send(message);
    }

    public String newHtmlTemplate(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("EmailTemplate", context);
    }

    public String newCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

}
