package com.xftxyz.blogsystem.controller;

import java.util.HashMap;
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
import com.xftxyz.blogsystem.jb.Follow;
import com.xftxyz.blogsystem.jb.User;
import com.xftxyz.blogsystem.service.BlogService;
import com.xftxyz.blogsystem.service.CommentService;
import com.xftxyz.blogsystem.service.FollowService;
import com.xftxyz.blogsystem.service.UserService;

@RestController
// @Api(value = "个人主页", tags = "个人主页")
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

    @GetMapping("/getUserInfo")
    // @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public R getUser(@RequestParam int uid) {
        return R.ok(userService.getById(uid));
    }

    @GetMapping("/getUserBlog")
    // @ApiOperation(value = "获取用户blog", notes = "获取用户blog")
    public R getUserBlog(@RequestParam int uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        return R.ok(blogService.listByMap(map));
    }

    @DeleteMapping("deletBlogOne")
    // @ApiOperation(value = "删除指定博客", notes = "删除指定博客")
    public R deleteBlogOne(@RequestParam int bid) {
        boolean flag = blogService.removeById(bid);
        return flag ? R.ok("删除成功") : R.error("删除失败");
    }

    @GetMapping("/getComment")
    // @ApiOperation(value = "获取博客评论", notes = "获取博客评论")
    public R getComment(@RequestParam int bid) {
        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        return R.ok(commentService.listByMap(map));
    }

    @DeleteMapping("/deleteComment")
    // @ApiOperation(value = "删除指定评论", notes = "删除指定评论")
    public R deleteComment(@RequestParam int cid) {
        boolean flag = commentService.removeById(cid);
        return flag ? R.ok("删除成功") : R.error("删除失败");
    }

    @GetMapping("/getFollow")
    // @ApiOperation(value = "获取关注列表", notes = "获取关注列表")
    public R getFollow(@RequestParam int uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid_self", uid);
        return R.ok(followService.listByMap(map));
    }

    @GetMapping("/getFans")
    // @ApiOperation(value = "获取粉丝列表", notes = "获取粉丝列表")
    public R getFans(@RequestParam int uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid_follow", uid);
        return R.ok(followService.listByMap(map));
    }

    @DeleteMapping("/deleteFollow")
    // @ApiOperation("取消关注")
    public R cancelFollow(@RequestParam int uid, @RequestParam int fid) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("uid_self", uid).ge("uid_follow", fid);
        boolean flag = followService.remove(queryWrapper);
        return flag ? R.ok("取消关注成功") : R.error("取消失败");
    }

    @PostMapping("/addBlog")
    // @ApiOperation("新增博客")
    // public R create_blog(@RequestParam int uid, @RequestParam int bno,
    // @RequestParam String btitle,
    // @RequestParam String bcontent, @RequestParam boolean bpublished,
    // @RequestParam Timestamp create_time,
    // @RequestParam int goodNum, @RequestParam int collectNum,
    // @RequestParam boolean state) {
    public R create_blog(@RequestBody Blog blog) {
        // Blog blog = new Blog(uid, bno, btitle, bcontent, bpublished, create_time,
        // goodNum, collectNum, state);
        boolean flag = blogService.save(blog);
        return flag ? R.ok("创建成功") : R.error("创建失败");
    }

    @PostMapping("/updateInfo")
    // @ApiOperation("修改个人信息")
    public R updateInfo(@RequestParam int uid, @RequestParam String name,
            @RequestParam String pwd, @RequestParam String email,
            @RequestParam String phone) {
        User user = userService.getById(uid);
        user.setUname(name);
        user.setPwd(pwd);
        user.setEmail(email);
        user.setPhone(phone);
        boolean flag = userService.updateById(user);
        return flag ? R.ok("修改成功") : R.error("修改失败");
    }

}
