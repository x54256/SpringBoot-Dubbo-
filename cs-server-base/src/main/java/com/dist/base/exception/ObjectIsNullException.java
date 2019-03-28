package com.dist.base.exception;


/**
 * 对象为null异常
 *
 * @author yinxp@dist.com.cn
 */
public class ObjectIsNullException extends MyCustomException {

    public ObjectIsNullException() {
        super();
    }

    public ObjectIsNullException(String s) {
        super(s);
    }
}
