package com.xftxyz.blogsystem.jb;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import lombok.Data;

/**
 * 
 * @TableName t_collect
 */
@TableName(value ="t_collect")
@Data
public class Collect implements Serializable {
    /**
     * 用户id
     */
    @TableField(value = "uid")
    @MppMultiId
    private Integer uid;

    /**
     * 博客id
     */
    @TableField(value = "bid")
    @MppMultiId
    private Integer bid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}