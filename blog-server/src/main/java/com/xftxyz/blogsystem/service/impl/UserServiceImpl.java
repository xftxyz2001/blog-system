package com.xftxyz.blogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.UserService;
import com.xftxyz.blogsystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 25810
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-04-11 13:52:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




