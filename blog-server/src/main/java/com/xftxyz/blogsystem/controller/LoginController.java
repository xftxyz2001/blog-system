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

/**
 * 登录接口
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 登录
     * 
     * @param session  用于保存登录信息
     * @param name     用户名
     * @param password 密码
     * @return 用户信息
     */
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
