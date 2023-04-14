package com.xftxyz.blogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.blogsystem.controller.utils.BlogException;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.controller.utils.Util;
import com.xftxyz.blogsystem.service.MailService;
import com.xftxyz.blogsystem.service.UserService;

/**
 * 忘记密码接口
 */
@RestController
@RequestMapping("/forgetPwd")
public class ForgetController {
    
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    private String subject = "博客系统验证码";
    private String content = "220博客系统 您的验证码:";

    /**
     * 检查用户是否存在
     * 
     * @param name 用户名
     * @return R
     */
    @PostMapping("/checkUser")
    public R<String> checkUser(@RequestParam String name) {
        boolean flag = userService.checkUser(name);
        if (flag) {
            return R.ok("用户存在");
        } else {
            throw new BlogException("用户不存在");
        }
    }

    /**
     * 检查邮箱是否正确
     * 
     * @param name  用户名
     * @param email 邮箱
     * @return R
     */
    @PostMapping("/checkEmail")
    public R<String> checkEmail(@RequestParam String name, @RequestParam String email) {
        boolean flag = userService.checkEmail(name, email);
        if (flag) {
            return R.ok("邮箱正确");
        } else {
            throw new BlogException("邮箱不正确");
        }
    }

    /**
     * 修改密码
     * 
     * @param email 邮箱
     * @param pwd   密码
     * @return 修改结果
     */
    @PostMapping("/rePwd")
    public R<String> changePassword(@RequestParam String email, @RequestParam String pwd) {
        boolean flag = userService.changePassword(email, pwd);
        if (flag) {
            return R.ok("修改密码成功");
        } else {
            throw new BlogException("修改密码失败");
        }
    }

    /**
     * 发送验证码
     * 
     * @param email 邮箱
     * @return 发送结果
     */
    @GetMapping("/ConfirmCode")
    public R<String> sendConfirmCode(@RequestParam String email) {
        String code = Util.getCode();
        mailService.sendSimpleMail(email, subject, content + code);
        return R.ok("验证码已发送至邮箱，请注意查收");
    }

}
