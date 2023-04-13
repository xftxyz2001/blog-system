package com.xftxyz.blogsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.blogsystem.jb.Blog;

/**
 * @author 25810
 * @description 针对表【t_blog】的数据库操作Service
 * @createDate 2023-04-11 13:52:37
 */
public interface BlogService extends IService<Blog> {

    IPage<Blog> getBlog(IPage<Blog> page);

    Blog getRandom(int num);

}
