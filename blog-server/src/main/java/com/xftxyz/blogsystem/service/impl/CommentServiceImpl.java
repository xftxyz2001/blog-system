package com.xftxyz.blogsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.Comment;
import com.xftxyz.blogsystem.mapper.CommentMapper;
import com.xftxyz.blogsystem.service.CommentService;

/**
 * @author 25810
 * @description 针对表【t_comment】的数据库操作Service实现
 * @createDate 2023-04-11 13:52:37
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean deleteCommentAll(int bid) {
        Wrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("bid", bid);
        commentMapper.delete(wrapper);
        return true;
    }

    @Override
    public String getCommentUser(Integer cid) {
        Comment comment = commentMapper.selectById(cid);
        return comment.getUserId();
    }

    @Override
    public List<Comment> getAllComment(Integer bid) {
        Wrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("bid", bid);
        return commentMapper.selectList(wrapper);
    }

}
