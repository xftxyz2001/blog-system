package com.xftxyz.blogsystem.jb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @TableId(value = "cid")
    private Integer cid;

    /**
     * 评论用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 博客id
     */
    @TableField(value = "bid")
    private String bid;

    /**
     * 评论父评论id
     */
    @TableField(value = "parent_comment_id")
    private String parentCommentId;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 评论时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}