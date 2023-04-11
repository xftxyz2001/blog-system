package com.xftxyz.blogsystem.utils;

import lombok.Data;

//表现层接口统一
@Data
public class R {
    private boolean flag;
    private Object data;
    private String msg;

    public static R ok() {
        R r = new R();
        r.setFlag(true);
        r.setMsg("操作成功");
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setFlag(true);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setFlag(false);
        r.setMsg("操作失败");
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.setFlag(false);
        r.setMsg(msg);
        return r;
    }

}
