package com.xftxyz.blogsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xftxyz.blogsystem.jb.Comment;

/**
* @author 25810
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2023-04-11 13:52:37
* @Entity com.xftxyz.blogsystem.jb.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




