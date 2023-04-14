package com.xftxyz.blogsystem.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<Blog> getRandom(int num) {
        List<Integer> ids = blogMapper.selectList(null).stream()
                .map(Blog::getBno).collect(Collectors.toList());
        // 打乱顺序
        Collections.shuffle(ids);
        // 取前num个，不足则返回全部
        List<Integer> selectedIds = ids.subList(0, num);
        return listByIds(selectedIds);
    }

    @Override
    public Integer increaseGoodNumAndGet(Integer bid) {
        Blog blog = blogMapper.selectById(bid);
        blog.setGoodnum(blog.getGoodnum() + 1);
        blogMapper.updateById(blog);
        return blog.getGoodnum();
    }

    @Override
    public Integer increaseCollectNumAndGet(Integer bid) {
        Blog blog = blogMapper.selectById(bid);
        blog.setCollectnum(blog.getCollectnum() + 1);
        blogMapper.updateById(blog);
        return blog.getCollectnum();
    }

    @Override
    public Integer decreaseGoodNumAndGet(Integer bid) {
        Blog blog = blogMapper.selectById(bid);
        blog.setGoodnum(blog.getGoodnum() - 1);
        blogMapper.updateById(blog);
        return blog.getGoodnum();
    }

    @Override
    public Integer decreaseCollectNumAndGet(Integer bid) {
        Blog blog = blogMapper.selectById(bid);
        blog.setCollectnum(blog.getCollectnum() - 1);
        blogMapper.updateById(blog);
        return blog.getCollectnum();
    }

    @Override
    public List<Blog> getUserBlog(Integer uid) {
        Wrapper<Blog> wrapper = new QueryWrapper<Blog>().eq("uid", uid);
        return blogMapper.selectList(wrapper);
    }

}
