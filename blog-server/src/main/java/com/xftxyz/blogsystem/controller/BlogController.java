package com.xftxyz.blogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.jb.Blog;
import com.xftxyz.blogsystem.service.BlogService;

/**
 * 博客操作接口
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    /**
     * 主页获取博客
     * 
     * @param cur  当前页
     * @param size 每页大小
     * @return 博客列表
     */
    @GetMapping("/getBlog")
    public R<IPage<Blog>> getBlog(@RequestParam(defaultValue = "1") Integer cur,
            @RequestParam(defaultValue = "2") Integer size) {
        IPage<Blog> pageInfo = new Page<>(cur, size);
        IPage<Blog> pageResult = blogService.getBlog(pageInfo);
        return R.ok(pageResult);
    }

    /**
     * 主页获取博客(随机)
     * 
     * @param num 要求返回的结果最大条数
     * @return
     */
    @GetMapping("/getRandomBlog")
    public R<Blog> getRandomBlog(@RequestParam(defaultValue = "2") Integer num) {
        return R.ok(blogService.getRandom(num));
    }

    // TODO: 2023/4/13 未完成

    /*
     * @PostMapping("/createBlog")
     * // @ApiOperation(value = "创建博客", notes = "创建博客")
     * public boolean create_blog(@RequestParam int uid, @RequestParam int
     * bno, @RequestParam String btitle,
     * 
     * @RequestParam String bcontent, @RequestParam boolean bpublished,
     * 
     * @RequestParam Timestamp create_time,
     * 
     * @RequestParam int goodNum, @RequestParam int collectNum,
     * 
     * @RequestParam boolean state) {
     * return blogService.addBlog(uid, bno, btitle, bcontent, bpublished,
     * create_time,
     * goodNum, collectNum, state);
     * }
     * 
     * @GetMapping("/getRandomBlog")
     * // @ApiOperation(value = "主页获取博客(随机)",notes ="主页获取博客(随机)")
     * public List<Blog> getRandomBlog(@RequestParam int num){
     * return blogService.getRandomBlog(num);
     * }
     * 
     * @GetMapping("/getUserAllBlog")
     * // @ApiOperation(value = "获取用户所有blog",notes ="获取用户所有blog")
     * public List<Blog> getUserAllBlog(@RequestParam int uid){
     * return blogService.getUserBlog_ALL(uid);
     * }
     * 
     * @GetMapping("/deleteBlog")
     * // @ApiOperation(value = "删除指定博客",notes = "删除指定博客")
     * public boolean deleteBlog(@RequestParam int bid){
     * return blogService.deleteBlog(bid);
     * }
     */
}
