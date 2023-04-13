package com.xftxyz.blogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xftxyz.blogsystem.controller.utils.BlogException;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.controller.utils.Util;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
// @Api(value = "登录接口",tags = {"登录接口"})
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public R<User> processLogin(HttpSession session, @RequestParam String name, @RequestParam String password) {
        User user = userService.login(name, password);
        if (user == null) {
            throw new BlogException("用户名或密码错误");
        }
        session.setAttribute(Util.SESSION_USER, user);
        return R.ok(user.mask());
    }
}
