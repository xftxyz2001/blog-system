package com.xftxyz.blogsystem.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public R doException(Exception ex) {

        ex.printStackTrace();
        return R.error("服务器故障,请稍后重试!");
    }
}
