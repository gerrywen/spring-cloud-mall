package com.mall.admin.base.api;

public class CommonResult<T> {
    private long status;
    private String message;
    private T data;
    private long code;

    protected CommonResult() {
    }

    /**
     * 构造方法
     *
     * @param status  服务器状态码
     * @param message 返回消息
     * @param data    返回数据
     * @param code    内部状态码： 1=>成功 0=>失败
     */
    protected CommonResult(long status, String message, T data, long code) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * 200
     */
    public static <T> CommonResult<T> success() {
        return success(null);
    }

    /**
     * 成功返回结果
     * 200
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return success(data, ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     * 200
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getStatus(), message, data, 1);
    }

    /**
     * 创建返回结果
     * 201
     */
    public static <T> CommonResult<T> created() {
        return created(null);
    }

    /**
     * 创建返回结果
     * 201
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> created(T data) {
        return created(data, ResultCode.CREATED.getMessage());
    }

    /**
     * 创建返回结果
     * 201
     *
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> created(T data, String message) {
        return new CommonResult<T>(ResultCode.CREATED.getStatus(), message, data, 1);
    }


    /**
     * 更新返回结果
     * 202
     */
    public static <T> CommonResult<T> accepted() {
        return accepted(null);
    }


    /**
     * 更新返回结果
     * 202
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> accepted(T data) {
        return accepted(data, ResultCode.ACCEPTED.getMessage());
    }


    /**
     * 更新返回结果
     * 202
     *
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> accepted(T data, String message) {
        return new CommonResult<T>(ResultCode.ACCEPTED.getStatus(), message, data, 1);
    }

    /**
     * 删除返回结果
     * 204
     */
    public static <T> CommonResult<T> deleted() {
        return deleted(null);
    }


    /**
     * 删除返回结果
     * 204
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> deleted(T data) {
        return deleted(data, ResultCode.DELETED.getMessage());
    }


    /**
     * 删除返回结果
     * 204
     *
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> deleted(T data, String message) {
        return new CommonResult<T>(ResultCode.DELETED.getStatus(), message, data, 1);
    }


    /**
     * 自定义失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getStatus(), errorCode.getMessage(), null, 0);
    }


    /**
     * 未登录返回结果
     * 401
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getStatus(), ResultCode.UNAUTHORIZED.getMessage(), data, 0);
    }

    /**
     * 未授权返回结果
     * 403
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getStatus(), ResultCode.FORBIDDEN.getMessage(), data, 0);
    }

    /**
     * 资源不存在
     * 404
     */
    public static <T> CommonResult<T> notFound(T data) {
        return new CommonResult<T>(ResultCode.NOT_FOUND.getStatus(), ResultCode.FORBIDDEN.getMessage(), data, 0);
    }

    /**
     * 参数验证失败返回结果
     * 405
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * 405
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getStatus(), message, null, 0);
    }

    /**
     * 失败返回结果
     * 500
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED.getMessage());
    }

    /**
     * 失败返回结果
     * 500
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getStatus(), message, null, 0);
    }


    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
