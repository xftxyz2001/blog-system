package com.xftxyz.blogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.UserService;

@RestController
// @Api(value = "登录接口",tags = {"登录接口"})
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public R processLogin(@RequestParam String name, @RequestParam String password) {
        User user = userService.login(name, password);
        return user != null ? R.ok("登录成功") : R.error("登录失败");
    }
}
