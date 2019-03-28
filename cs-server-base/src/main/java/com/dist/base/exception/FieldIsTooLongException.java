package com.dist.base.exception;

/**
 * 字段过长异常
 *
 * @author yinxp@dist.com.cn
 */
public class FieldIsTooLongException extends MyCustomException {

    public FieldIsTooLongException() {
        super();
    }

    public FieldIsTooLongException(String s) {
        super(s);
    }
}
