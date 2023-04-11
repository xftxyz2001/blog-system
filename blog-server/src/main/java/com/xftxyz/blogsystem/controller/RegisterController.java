package com.xftxyz.blogsystem.controller;

import com.xftxyz.blogsystem.service.UserService;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
// @Api(value = "注册接口",tags = {"注册接口"})
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    private MailService mailService;

    private String subject = "博客系统验证码";
    private String content = "220博客系统 您的验证码:";

    public boolean judgeRude(String name) {
        String[] illegal = { "你妈", "傻逼", "煞笔", "沙比", "骚逼", "烧杯", "尼玛", "sb", "Sb", "SB", "sB", "tmd", "TMD" };
        for (String s : illegal) {
            if (name.contains(s))
                return false;
        }
        return true;
    }

    // @ApiOperation(value = "注册方法",notes = "注册方法")
    @PostMapping("/actReg")
    public R registerHandle(@RequestParam String name, @RequestParam String pwd, @RequestParam String email) {
        if (judgeRude(name)) {
            User user = new User();
            user.setUname(name);
            user.setPwd(pwd);
            user.setEmail(email);
            boolean save = userService.save(user);
            return save ? R.ok("注册成功") : R.error("注册失败");
        }
        return R.error("注册失败，请勿使用敏感词汇做用户名");
    }

    // @ApiOperation(value = "邮箱验证码",notes = "邮箱验证码")
    @GetMapping("/ConfirmCode")
    public R ConfirmCodeSend(@RequestParam String email) {
        Random r = new Random();
        int randomNum = r.nextInt(9) + 1;
        int temp = 0;
        for (int i = 0; i < 6; i++) {
            temp = temp * 10 + randomNum;
            randomNum = r.nextInt(9) + 1;
        }
        content = content + temp;
        mailService.sendSimpleMail(email, subject, content);
        content = "220博客系统 您的验证码:";
        return R.ok(temp);
    }
}
