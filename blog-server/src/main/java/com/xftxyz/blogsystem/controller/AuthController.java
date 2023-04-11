package com.xftxyz.blogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.MailService;
import com.xftxyz.blogsystem.service.UserService;
import com.xftxyz.blogsystem.utils.R;
import com.xftxyz.blogsystem.utils.Util;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    private static final String subject = "博客系统验证码";
    private static final String content = "220博客系统 您的验证码:";

    @PostMapping("/login")
    public R processLogin(@RequestParam String name, @RequestParam String password) {
        User user = userService.login(name, password);
        return user != null ? R.ok("登录成功") : R.error("登录失败");
    }

    @PostMapping("/register")
    public R processRegister(@RequestParam String name, @RequestParam String password, @RequestParam String email) {
        User user = new User();
        // TODO: 敏感词过滤
        user.setUname(name);
        user.setPwd(password);
        user.setEmail(email);
        boolean save = userService.save(user);
        return save ? R.ok("注册成功") : R.error("注册失败");
    }

    @GetMapping("/code")
    public R processCode(@RequestParam String email) {
        String code = Util.getCode();
        mailService.sendSimpleMail(email, subject, content + code);
        return R.ok("验证码发送成功，请注意查收");
    }
}
