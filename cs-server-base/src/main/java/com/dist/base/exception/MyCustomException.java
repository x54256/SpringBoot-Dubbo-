package com.dist.base.exception;

/**
 * @author yujx
 * @date 2019/02/14 15:37
 */
public abstract class MyCustomException extends RuntimeException {

    public MyCustomException() {
        super();
    }

    public MyCustomException(String s) {
        super(s);
    }

}
