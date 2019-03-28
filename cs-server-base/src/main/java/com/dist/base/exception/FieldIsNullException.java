package com.dist.base.exception;


/**
 * 字段为空异常
 *
 * @author yinxp@dist.com.cn
 */
public class FieldIsNullException extends MyCustomException {

    public FieldIsNullException() {
        super();
    }

    public FieldIsNullException(String s) {
        super(s);
    }
}
