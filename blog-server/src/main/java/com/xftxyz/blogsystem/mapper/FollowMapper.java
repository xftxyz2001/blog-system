package com.xftxyz.blogsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.xftxyz.blogsystem.jb.Follow;

/**
* @author 25810
* @description 针对表【t_follow】的数据库操作Mapper
* @createDate 2023-04-11 13:52:37
* @Entity com.xftxyz.blogsystem.jb.Follow
*/
@Mapper
public interface FollowMapper extends MppBaseMapper<Follow> {

}




