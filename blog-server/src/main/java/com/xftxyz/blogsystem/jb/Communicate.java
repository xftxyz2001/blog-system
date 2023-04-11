package com.xftxyz.blogsystem.jb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
    private Integer uid1;

    /**
     * 用户2
     */
    @TableId(value = "uid2")
    private Integer uid2;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}