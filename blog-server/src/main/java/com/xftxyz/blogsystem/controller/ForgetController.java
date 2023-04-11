package com.xftxyz.blogsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.controller.utils.Util;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.MailService;
import com.xftxyz.blogsystem.service.UserService;

@RestController
// @Api(value = "忘记密码接口",tags = {"忘记密码接口"})
@RequestMapping("/forgetPwd")
public class ForgetController {
    @Autowired
    UserService userService;
    @Autowired
    private MailService mailService;
    private String subject = "博客系统验证码";
    private String content = "220博客系统 您的验证码:";

    // @ApiOperation(value = "检查用户是否存在",notes = "检查用户是否存在")
    @PostMapping("/checkUser")
    public R IfUser(@RequestParam String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("uname", name);
        List<User> users = userService.listByMap(map);
        boolean flag = users.size() >= 1;
        return flag ? R.ok("用户存在") : R.error("用户不存在");
    }

    // @ApiOperation(value = "检查email是否正确",notes = "检查email是否正确")
    @PostMapping("/checkEmail")
    public R IfEmail(@RequestParam String name, @RequestParam String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("uname", name);
        map.put("email", email);
        List<User> users = userService.listByMap(map);
        boolean flag = users.size() >= 1;
        return flag ? R.ok("邮箱正确") : R.error("邮箱不正确");
    }

    // @ApiOperation(value = "修改密码",notes = "修改密码")
    @PostMapping("/rePwd")
    public R RePwd(@RequestParam String email, @RequestParam String pwd) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        user.setPwd(pwd);
        boolean flag = userService.updateById(user);
        return flag ? R.ok("修改密码成功") : R.error("修改密码失败");
    }

    // @ApiOperation(value = "邮箱验证码",notes = "邮箱验证码")
    @GetMapping("/ConfirmCode")
    public R confirmCodeSend(@RequestParam String email) {
        String code = Util.getCode();
        mailService.sendSimpleMail(email, subject, content + code);
        return R.ok("验证码发送成功，请注意查收");
    }

}
