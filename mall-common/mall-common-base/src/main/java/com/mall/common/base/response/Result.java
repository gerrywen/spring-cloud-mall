package com.mall.common.base.response;

/**
 * program: spring-cloud-mall->Result
 * description: 返回结果
 * author: gerry
 * created: 2019-11-23 10:40
 **/
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    /**
     *  成功时候的调用
     * */
    public static  <T> Result<T> success(T data){
        Result<T> result = new Result<T>(data);
        result.code = CodeMsg.SUCCESS.getCode();
        return new Result<T>(data);
    }

    /**
     * 成功返回结果
     *
     * @param  message 提示信息
     */
    public static <T> Result<T> success(String message) {
        return new Result<T>(CodeMsg.SUCCESS.getCode(), message);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(data,CodeMsg.SUCCESS.getCode(), message);
    }

    /**
     *  成功时候的调用
     * */
    public static  <T> Result<T> success(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    /**
     *  失败时候的调用
     * */
    public static  <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg) {
        if(codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
