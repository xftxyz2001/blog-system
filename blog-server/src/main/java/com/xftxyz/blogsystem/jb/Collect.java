package com.xftxyz.blogsystem.jb;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
    @TableId(value = "uid")
    private Integer uid;

    /**
     * 博客id
     */
    @TableId(value = "bid")
    private Integer bid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}