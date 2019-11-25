package com.mall.enums;

/**
 * program: chengjie-ts->ResultCode
 * description: 枚举了一些常用API操作码
 * author: gerry
 * created: 2019-08-05 11:14
 **/
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"), // [GET]：服务器成功返回用户请求的数据，该操作是幂等的
    CREATED(201,"请求成功"), // [POST/PUT/PATCH]：用户新建或修改数据成功。
    ACCEPTED(202,"请求成功"), // [*]：表示一个请求已经进入后台排队（异步任务）
    DELETED(204,"删除成功"), //  [DELETE]：用户删除数据成功。
    BAD_REQUEST(400,"请求不存在"), // [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
    UNAUTHORIZED(401, "暂未登录或token已经过期"), // [*]：表示用户没有权限（令牌、用户名、密码错误）。
    FORBIDDEN(403, "没有相关权限"),// [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
    NOT_FOUND(404, "资源不存在"), // [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
    NOT_ACCEPTABLE(406, "参数验证失败"), //  [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
    GONE(410,"资源永久删除"), // 用户请求的资源被永久删除，且不会再得到的。
    VALIDATE_FAILED(422, "参数验证失败"), //  [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
    FAILED(500, "操作失败,内部错误"); // [*]：服务器发生错误，用户将无法判断发出的请求是否成功。

    private long status;
    private String message;

    private ResultCode(long status, String message) {
        this.status = status;
        this.message = message;
    }

    public long getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
