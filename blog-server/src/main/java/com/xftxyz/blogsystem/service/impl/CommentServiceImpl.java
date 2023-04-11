package com.xftxyz.blogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.Comment;
import com.xftxyz.blogsystem.service.CommentService;
import com.xftxyz.blogsystem.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
 * @author 25810
 * @description 针对表【t_comment】的数据库操作Service实现
 * @createDate 2023-04-11 13:52:37
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}
