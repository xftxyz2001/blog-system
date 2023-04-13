package com.xftxyz.blogsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.blogsystem.jb.Blog;
import com.xftxyz.blogsystem.mapper.BlogMapper;
import com.xftxyz.blogsystem.service.BlogService;

/**
 * @author 25810
 * @description 针对表【t_blog】的数据库操作Service实现
 * @createDate 2023-04-11 13:52:37
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
        implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public IPage<Blog> getBlog(IPage<Blog> page) {
        return blogMapper.selectPage(page, null);
    }

    @Override
    public Blog getRandom(int num) {
        List<Integer> ids = blogMapper.selectList(null).stream()
                .map(Blog::getBno).collect(Collectors.toList());
        // TODO: 选出num个个id
        int index = (int) (Math.random() * ids.size());
        return blogMapper.selectById(ids.get(index));
    }

}
