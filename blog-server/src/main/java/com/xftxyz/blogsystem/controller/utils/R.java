package com.xftxyz.blogsystem.controller.utils;

import lombok.Data;

/**
 * 表现层接口统一
 */
@Data
public class R<T> {
    Boolean flag;
    String msg;
    T data;

    public static R<Object> ok() {
        R<Object> r = new R<>();
        r.setFlag(true);
        r.setMsg("操作成功");
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setFlag(true);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static R<Object> error() {
        R<Object> r = new R<>();
        r.setFlag(false);
        r.setMsg("操作失败");
        return r;
    }

    public static R<Object> error(String msg) {
        R<Object> r = new R<>();
        r.setFlag(false);
        r.setMsg(msg);
        return r;
    }

}
