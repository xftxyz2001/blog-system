package com.xftxyz.blogsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.blogsystem.jb.User;

/**
 * @author 25810
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2023-04-11 13:52:37
 */
public interface UserService extends IService<User> {

    User login(String name, String password);

}
