package com.xftxyz.blogsystem.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.blogsystem.jb.Comment;

/**
 * @author 25810
 * @description 针对表【t_comment】的数据库操作Service
 * @createDate 2023-04-11 13:52:37
 */
public interface CommentService extends IService<Comment> {

    boolean deleteCommentAll(int bid);

    String getCommentUser(Integer cid);

    List<Comment> getAllComment(Integer bid);

}
