package com.xftxyz.blogsystem.mapper;

import com.xftxyz.blogsystem.jb.Blog;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 25810
 * @description 针对表【t_blog】的数据库操作Mapper
 * @createDate 2023-04-11 13:52:37
 * @Entity com.xftxyz.blogsystem.jb.Blog
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}
