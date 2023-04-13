package com.xftxyz.blogsystem.jb;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xftxyz.blogsystem.controller.utils.Util;

import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户名
     */
    @TableField(value = "uname")
    private String uname;

    /**
     * 密码
     */
    @TableField(value = "pwd")
    private String pwd;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 电话号码
     */
    @TableField(value = "phone")
    private String phone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public User mask() {
        User user = new User();
        user.setUid(this.getUid());
        user.setUname(this.getUname());
        user.setPwd(Util.PASSWORD_MASK);
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());

        return null;
    }
}