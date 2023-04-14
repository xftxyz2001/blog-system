package com.xftxyz.blogsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.jb.Blog;
import com.xftxyz.blogsystem.jb.Comment;
import com.xftxyz.blogsystem.jb.Follow;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.BlogService;
import com.xftxyz.blogsystem.service.CommentService;
import com.xftxyz.blogsystem.service.FollowService;
import com.xftxyz.blogsystem.service.UserService;

/**
 * 个人主页
 */
@RestController
@RequestMapping("/myHome")
public class MyHomeController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo")
    public R<User> getUser(@RequestParam Integer uid) {
        return R.ok(userService.getById(uid));
    }

    /**
     * 获取用户blog
     * 
     * @param uid
     * @return
     */
    @GetMapping("/getUserBlog")
    public R<List<Blog>> getUserBlog(@RequestParam Integer uid) {
        return R.ok(blogService.getUserBlog(uid));
    }

    /**
     * 删除指定博客
     * 
     * @param bid
     * @return
     */
    @DeleteMapping("deletBlogOne")
    public R<Object> deleteBlogOne(@RequestParam Integer bid) {
        boolean flag = blogService.removeById(bid);
        return flag ? R.ok("删除成功") : R.error("删除失败");
    }

    /**
     * 获取博客评论
     * 
     * @param bid
     * @return
     */
    @GetMapping("/getComment")
    public R<List<Comment>> getComment(@RequestParam Integer bid) {
        return R.ok(commentService.getAllComment(bid));
    }

    /**
     * 删除指定评论
     * 
     * @param cid
     * @return
     */
    @DeleteMapping("/deleteComment")
    public R<Object> deleteComment(@RequestParam Integer cid) {
        boolean flag = commentService.removeById(cid);
        return flag ? R.ok("删除成功") : R.error("删除失败");
    }

    /**
     * 获取关注列表
     * 
     * @param uid
     * @return
     */
    @GetMapping("/getFollow")
    public R<Object> getFollow(@RequestParam Integer uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid_self", uid);
        return R.ok(followService.listByMap(map));
    }

    /**
     * 获取粉丝列表
     * 
     * @param uid
     * @return
     */
    @GetMapping("/getFans")
    public R<Object> getFans(@RequestParam Integer uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid_follow", uid);
        return R.ok(followService.listByMap(map));
    }

    /**
     * 取消关注
     * 
     * @param uid
     * @param fid
     * @return
     */
    @DeleteMapping("/deleteFollow")
    public R<Object> cancelFollow(@RequestParam Integer uid, @RequestParam Integer fid) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("uid_self", uid).ge("uid_follow", fid);
        boolean flag = followService.remove(queryWrapper);
        return flag ? R.ok("取消关注成功") : R.error("取消失败");
    }

    /**
     * 新增博客
     * 
     * @param blog
     * @return
     */
    @PostMapping("/addBlog")
    public R<Object> create_blog(@RequestBody Blog blog) {
        boolean flag = blogService.save(blog);
        return flag ? R.ok("创建成功") : R.error("创建失败");
    }

    /**
     * 修改个人信息
     */
    @PostMapping("/updateInfo")
    public R<Object> updateInfo(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        return flag ? R.ok("修改成功") : R.error("修改失败");
    }

}
