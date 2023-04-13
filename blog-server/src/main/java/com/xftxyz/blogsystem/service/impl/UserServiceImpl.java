package com.xftxyz.blogsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.mapper.UserMapper;
import com.xftxyz.blogsystem.service.UserService;

/**
 * @author 25810
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-04-11 13:52:37
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String name, String password) {
        List<User> selectByUnameAndPwd = userMapper.selectByUnameAndPwd(name, password);
        if (selectByUnameAndPwd.size() == 1) {
            return selectByUnameAndPwd.get(0);
        }
        return null;
    }

    @Override
    public boolean checkUser(String name) {
        return userMapper.countByUname(name) > 0;
    }

    @Override
    public boolean checkEmail(String name, String email) {
        return userMapper.countByUnameAndEmail(name, email) > 0;
    }

    @Override
    public boolean changePassword(String email, String pwd) {
        // 找到对应email的用户
        List<User> users = userMapper.selectByEmail(email);
        if (users == null || users.size() < 1) {
            return false;
        }
        User user = users.get(0);
        // 修改密码
        user.setPwd(pwd);
        return userMapper.updateById(user) > 0;
    }

}
