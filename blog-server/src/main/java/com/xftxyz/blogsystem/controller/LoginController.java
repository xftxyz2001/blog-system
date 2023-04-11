package com.xftxyz.blogsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;

@RestController
// @Api(value = "登录接口", tags = { "登录接口" })
public class LoginController {
    // @Autowired
    // private UserB userB;

    // @ApiOperation(value = "登录方法", notes = "登录方法")
    @GetMapping("/login")
    public String process_login(@RequestParam String name, @RequestParam String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("uname", name);
        map.put("pwd", password);
        // List<User> users = userB.selectByMap(map);
        // boolean flag = users.size() == 1;
        // return new R(flag,flag?"登录成功":"登陆失败");
        return "hello";
    }
}