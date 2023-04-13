package com.xftxyz.blogsystem.controller.utils;

/**
 * 系统异常：用于业务逻辑异常
 */
public class BlogException extends RuntimeException {

    public BlogException() {
    }

    public BlogException(String arg0) {
        super(arg0);
    }

    public BlogException(Throwable arg0) {
        super(arg0);
    }

    public BlogException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public BlogException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
