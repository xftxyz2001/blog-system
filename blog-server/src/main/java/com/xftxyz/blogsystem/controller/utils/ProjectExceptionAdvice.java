package com.xftxyz.blogsystem.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {

    // 处理自定义异常
    @ExceptionHandler(BlogException.class)
    public R<Object> doBlogException(BlogException e) {
        return R.error(e.getMessage());
    }

    // 处理其他异常
    @ExceptionHandler(Exception.class)
    public R<Object> doException(Exception e) {
        e.printStackTrace();
        return R.error("服务器故障，请稍后重试!" + e);
    }
}
