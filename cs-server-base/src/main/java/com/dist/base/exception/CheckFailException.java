package com.dist.base.exception;

/**
 * 验证失败异常
 *
 * @author yinxp@dist.com.cn
 */
public class CheckFailException extends MyCustomException {

    public CheckFailException() {
        super();
    }

    public CheckFailException(String s) {
        super(s);
    }
}
