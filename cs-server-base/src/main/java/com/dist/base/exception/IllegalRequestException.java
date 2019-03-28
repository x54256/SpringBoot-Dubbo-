package com.dist.base.exception;

/**
 * 非法请求
 *
 * @author yinxp@dist.com.cn
 */
public class IllegalRequestException extends MyCustomException {

    public IllegalRequestException(){
        super();
    }

    public IllegalRequestException(String s){
        super(s);
    }

}
