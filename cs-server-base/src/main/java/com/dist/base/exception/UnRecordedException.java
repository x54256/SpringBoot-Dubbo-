package com.dist.base.exception;

/**
 * 记录不存在异常
 *
 * @author yinxp@dist.com.cn
 */
public class UnRecordedException extends MyCustomException {

    public UnRecordedException() {
        super();
    }

    public UnRecordedException(String s) {
        super(s);
    }
}
