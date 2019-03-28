package com.dist.base.exception;

/**
 * 记录已经存在异常
 *
 * @author yinxp@dist.com.cn
 */
public class ExistedRecordedException extends MyCustomException {
    public ExistedRecordedException() {
        super();
    }

    public ExistedRecordedException(String s) {
        super(s);
    }
}
