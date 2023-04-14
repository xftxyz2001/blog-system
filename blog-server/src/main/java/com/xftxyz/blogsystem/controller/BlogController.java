package com.xftxyz.blogsystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xftxyz.blogsystem.controller.utils.R;
import com.xftxyz.blogsystem.jb.Blog;
import com.xftxyz.blogsystem.jb.Comment;
import com.xftxyz.blogsystem.service.BlogService;
import com.xftxyz.blogsystem.service.CommentService;

/**
 * 博客操作接口
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

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
        List<Blog> randomBlogs = blogService.getRandom(num);
        return R.ok(randomBlogs.get(0));
    }

    /**
     * 增加指定博客点赞量
     * 
     * @param bid 博客id
     * @return 最新点赞量
     */
    @PostMapping("/increaseGoodNum")
    public R<Integer> increaseGoodNum(@RequestParam Integer bid) {
        return R.ok(blogService.increaseGoodNumAndGet(bid));
    }

    /**
     * 增加指定博客收藏量
     * 
     * @param bid 博客id
     * @return 最新收藏量
     */
    @PostMapping("/increaseCollectNum")
    public R<Integer> increaseCollectNum(@RequestParam Integer bid) {
        return R.ok(blogService.increaseCollectNumAndGet(bid));
    }

    /**
     * 减少指定博客点赞量
     * 
     * @param bid 博客id
     * @return 最新点赞量
     */
    @PostMapping("/decreaseGoodNum")
    public R<Integer> decreaseGoodNum(@RequestParam Integer bid) {
        return R.ok(blogService.decreaseGoodNumAndGet(bid));
    }

    /**
     * 减少指定博客收藏量
     * 
     * @param bid 博客id
     * @return 最新收藏量
     */
    @PostMapping("/decreaseCollectNum")
    public R<Integer> decreaseCollectNum(@RequestParam Integer bid) {
        return R.ok(blogService.decreaseCollectNumAndGet(bid));
    }

    /**
     * 获取当前博客的所有评论
     * 
     * @param bid 博客id
     * @return 评论列表
     */
    @PostMapping("/getAllComment")
    public R<List<Comment>> getAllComment(@RequestParam Integer bid) {
        return R.ok(commentService.getAllComment(bid));
    }

    /**
     * 创建新的评论
     * 
     * @param bid       博客id
     * @param parentId  父评论id
     * @param content   评论内容
     * @param timestamp 评论时间
     * @return 是否成功
     */
    @PostMapping("/createComment")
    public R<Object> create_comment(@RequestBody Comment comment) {
        comment.setCreateTime(new Date());
        commentService.save(comment);
        return R.ok();
    }

    /**
     * 删除指定的评论
     * 
     * @param cid 评论id
     * @return 是否成功
     */
    @PostMapping("/deleteCommentOne")
    public R<Object> deleteCommentOne(@RequestParam Integer cid) {
        commentService.removeById(cid);
        return R.ok();
    }

    /**
     * 删除指定博客的所有评论
     * 
     * @param bid 博客id
     * @return 是否成功
     */
    @PostMapping("/deleteCommentAll")
    public R<Object> deleteCommentAll(@RequestParam Integer bid) {
        commentService.deleteCommentAll(bid);
        return R.ok();
    }

    /**
     * 返回指定评论的用户id
     * 
     * @param cid 评论id
     * @return 用户id
     */
    @PostMapping("/getCommentUser")
    public R<String> getCommentUser(@RequestParam Integer cid) {
        return R.ok(commentService.getCommentUser(cid));
    }

}
