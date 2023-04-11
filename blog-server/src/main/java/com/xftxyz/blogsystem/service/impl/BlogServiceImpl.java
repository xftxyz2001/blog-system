package com.xftxyz.blogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.Blog;
import com.xftxyz.blogsystem.service.BlogService;
import com.xftxyz.blogsystem.mapper.BlogMapper;
import org.springframework.stereotype.Service;

/**
* @author 25810
* @description 针对表【t_blog】的数据库操作Service实现
* @createDate 2023-04-11 13:52:37
*/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
    implements BlogService{

}




