package com.xftxyz.blogsystem.jb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
    @TableId(value = "uid_self")
    private Integer uidSelf;

    /**
     * 关注用户id
     */
    @TableId(value = "uid_follow")
    private Integer uidFollow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}