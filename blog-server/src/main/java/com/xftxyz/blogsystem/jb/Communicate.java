package com.xftxyz.blogsystem.jb;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import lombok.Data;

/**
 * 
 * @TableName t_communicate
 */
@TableName(value ="t_communicate")
@Data
public class Communicate implements Serializable {
    /**
     * 用户1
     */
    @TableId(value = "uid1")
    @MppMultiId
    private Integer uid1;

    /**
     * 用户2
     */
    @TableId(value = "uid2")
    @MppMultiId
    private Integer uid2;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}