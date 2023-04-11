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
 * @TableName t_blog
 */
@TableName(value ="t_blog")
@Data
public class Blog implements Serializable {
    /**
     * 博客id
     */
    @TableId(value = "bid")
    private Integer bid;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 用户博客排序
     */
    @TableField(value = "bno")
    private Integer bno;

    /**
     * 博客标题
     */
    @TableField(value = "btitle")
    private String btitle;

    /**
     * 博客内容
     */
    @TableField(value = "bcontent")
    private String bcontent;

    /**
     * 博客是否发布
     */
    @TableField(value = "bpublished")
    private Boolean bpublished;

    /**
     * 博客创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 博客更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 点赞量
     */
    @TableField(value = "goodNum")
    private Integer goodnum;

    /**
     * 收藏量
     */
    @TableField(value = "collectNum")
    private Integer collectnum;

    /**
     * 审核状态
     */
    @TableField(value = "state")
    private Boolean state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}