package com.xftxyz.blogsystem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xftxyz.blogsystem.jb.User;

/**
 * @author 25810
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2023-04-11 13:52:37
 * @Entity com.xftxyz.blogsystem.jb.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectByUnameAndPwd(@Param("uname") String uname, @Param("pwd") String pwd);
}
