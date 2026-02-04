package com.itheima.exception;

public class ExistChildException extends RuntimeException{
    private int code;
    //错误码
    public ExistChildException(Integer code,String msg){
        super(msg);
        this.code=code;
    }
    //构造函数
    //获取id
}
