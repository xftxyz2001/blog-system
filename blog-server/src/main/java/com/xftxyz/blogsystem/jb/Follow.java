package com.xftxyz.blogsystem.jb;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import lombok.Data;

/**
 * 
 * @TableName t_follow
 */
@TableName(value ="t_follow")
@Data
public class Follow implements Serializable {
    /**
     * 用户id
     */
    @TableField(value = "uid_self")
    @MppMultiId
    private Integer uidSelf;

    /**
     * 关注用户id
     */
    @TableField(value = "uid_follow")
    @MppMultiId
    private Integer uidFollow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}