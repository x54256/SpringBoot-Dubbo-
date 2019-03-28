package com.dist.base.exception;

/**
 * 参数不合法
 *
 * @author yinxp@dist.com.cn
 */
public class IllegalParameterException extends MyCustomException {

    public IllegalParameterException() {
        super();
    }

    public IllegalParameterException(String s) {
        super(s);
    }
}
