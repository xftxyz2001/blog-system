package com.xftxyz.blogsystem.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.blogsystem.controller.utils.BlogException;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.controller.utils.Util;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.MailService;
import com.xftxyz.blogsystem.service.UserService;

/**
 * 注册接口
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    private String subject = "博客系统验证码";
    private String content = "【220博客系统】您的验证码: ";

    private static final String[] ILLEGAL_WORDS = { "你妈", "傻逼", "煞笔", "沙比", "骚逼", "烧杯", "尼玛", "sb", "tmd" };

    private static final Pattern PATTERN = Pattern.compile("\\b(" + String.join("|", ILLEGAL_WORDS) + ")\\b",
            Pattern.CASE_INSENSITIVE);

    public boolean containSensitiveWords(String name) {
        return PATTERN.matcher(name).find();
    }

    /**
     * 注册方法，如果用户名包含敏感词汇，就注册失败，否则注册成功
     * 
     * @param name  用户名
     * @param pwd   密码
     * @param email 邮箱
     * @return 注册结果
     */
    @PostMapping("/actReg")
    public R<String> registerHandle(@RequestParam String name, @RequestParam String pwd, @RequestParam String email) {
        // 如果用户名包含敏感词汇
        if (containSensitiveWords(name)) {
            throw new BlogException("注册失败，请勿使用敏感词汇做用户名");
        }

        User user = new User();
        user.setUname(name);
        user.setPwd(pwd);
        user.setEmail(email);
        boolean save = userService.save(user);
        if (save) {
            return R.ok("注册成功");
        } else {
            throw new BlogException("注册失败");
        }
    }

    /**
     * 发送验证码
     * 
     * @param email 邮箱
     * @return 发送结果
     */
    @GetMapping("/ConfirmCode")
    public R<String> ConfirmCodeSend(@RequestParam String email) {
        String code = Util.getCode();
        mailService.sendSimpleMail(email, subject, content + code);
        return R.ok("验证码已发送至邮箱，请注意查收");
    }
}
