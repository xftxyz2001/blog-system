package com.xftxyz.blogsystem.controller;

import com.xftxyz.blogsystem.service.BlogService;
import com.xftxyz.blogsystem.jb.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @Api(value = "博客操作接口", tags = "博客操作接口")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/getRandomBlog")
    // @ApiOperation(value = "主页获取博客(随机)",notes ="主页获取博客(随机)")
    public Blog getRandomBlog(@RequestParam int num) {
        // TODO:随机
        return blogService.getById(num);
    }
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
