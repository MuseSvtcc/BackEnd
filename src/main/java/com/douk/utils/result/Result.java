package com.douk.utils.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;//状态码
    private String message;//返回信息
    private T data;//数据

    //私有化
    private Result(){

    }
    public static <T>Result<T> custom(Integer code ,String message,T data){
        Result<T> result=new Result<>();
        if(data!=null){
            result.setData(data);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result=new Result<>();
        //封装数据
        if(body!=null){
            result.setData(body);
        }
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }
    //成功
    public static <T> Result<T> ok(){
        return build(null,ResultCodeEnum.SUCCESS);
    }
    public static <T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }
    //失败
    public static <T> Result<T> fail(){
        return build(null,ResultCodeEnum.FAIL);
    }
    public static <T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }
    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}


