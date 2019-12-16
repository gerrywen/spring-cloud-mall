package com.mall.common.base.response;

/**
 * program: spring-cloud-mall->CodeMsg
 * description: 返回状态码
 * author: gerry
 * created: 2019-11-23 10:39
 **/
public class CodeMsg {

    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");

    /**
     * 通用的错误码
     */
    public static CodeMsg BAD_REQUEST = new CodeMsg(400, "Bad Request");
    public static CodeMsg UNAUTHORIZED = new CodeMsg(401, "未授权");
    public static CodeMsg FORBIDDEN = new CodeMsg(403, "Forbidden");
    public static CodeMsg TOKEN_OVER = new CodeMsg(403, "Token已过期");
    public static CodeMsg NOT_FOUND = new CodeMsg(404, "Not Found");

    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
    public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问太频繁！");

    /**
     * 登录模块 5002XX
     */
    public static CodeMsg REGISTER_SUCCESS = new CodeMsg(0, "注册成功");
    public static CodeMsg VERIFY_CODE_SUCCESS = new CodeMsg(0, "获取验证码成功");
    public static CodeMsg CHANGE_PASSWORD_SUCCESS = new CodeMsg(0, "密码修改成功");

    public static CodeMsg LOGIN_VERIFY_ERROR = new CodeMsg(500200, "验证码错误");
    public static CodeMsg LOGIN_USER_EXIST = new CodeMsg(500201, "该用户已经存在");

    public static CodeMsg LOGIN_ERROR = new CodeMsg(500210, "用户未登录");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");


    /**
     * 商品模块 5003XX
     */
    public static CodeMsg BRAND_NOT_EXIST = new CodeMsg(500300, "品牌不存在");

    /**
     * 订单模块 5004XX
     */
    public static CodeMsg CART_ADD_ERROR = new CodeMsg(500400, "添加购物车失败");
    public static CodeMsg ORDER_USER_NOT_EXIST = new CodeMsg(500401, "下单用户信息错误");
    public static CodeMsg ORDER_STOCK_NOT_ENOUGH = new CodeMsg(500402, "库存不足，无法下单");
    public static CodeMsg ORDER_COUPON_NOT_USE = new CodeMsg(500403, "该优惠券不可用");
    public static CodeMsg ORDER_INTEGRAL_NOT_USE = new CodeMsg(500404, "积分不可用");
    public static CodeMsg ORDER_NOT_TIME_OUT = new CodeMsg(500404, "暂无超时订单");

    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500410, "订单不存在");


    /**
     * 秒杀模块 5005XX
     */
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");
    public static CodeMsg MIAOSHA_FAIL = new CodeMsg(500502, "秒杀失败");

    /**
     * 优惠券模块 5006XX
     */
    public static CodeMsg COUPON_RECEIVE_SUCCESS= new CodeMsg(0, "领取成功");
    public static CodeMsg COUPON_NOT_EXIST = new CodeMsg(500600, "优惠券不存在");
    public static CodeMsg COUPON_OVER = new CodeMsg(500601, "优惠券已经领完了");
    public static CodeMsg COUPON_NOT_START_TIME = new CodeMsg(500602, "优惠券还没到领取时间");
    public static CodeMsg COUPON_ALREADY_RECEIVE = new CodeMsg(500603, "您已经领取过该优惠券");
    public static CodeMsg COUPON_GET_LIST_ERROR = new CodeMsg(500603, "获取优惠券列表失败");


    /**
     * 会员模块 5007XX address
     */
    public static CodeMsg MEMBER_FOLLOW_BRAND_ERROR = new CodeMsg(500600, "关注失败");
    public static CodeMsg MEMBER_CANCDL_FOLLOW_ERROR = new CodeMsg(500601, "取消失败");
    public static CodeMsg MEMBER_COLLECTION_PRODUCT_ERROR = new CodeMsg(500603, "收藏失败");
    public static CodeMsg MEMBER_CANCDL_COLLECTION_ERROR = new CodeMsg(500604, "取消收藏失败");
    public static CodeMsg MEMBER_CREATE_HISTORY_ERROR = new CodeMsg(500605, "创建浏览记录失败");
    public static CodeMsg MEMBER_DELETE_HISTORY_ERROR = new CodeMsg(500606, "删除浏览记录失败");

    public static CodeMsg MEMBER_ADDRESS_ADD_ERROR = new CodeMsg(500610, "添加收货地址失败");
    public static CodeMsg MEMBER_ADDRESS_DELETE_ERROR = new CodeMsg(500611, "删除收货地址失败");
    public static CodeMsg MEMBER_ADDRESS_UPDATE_ERROR = new CodeMsg(500612, "更新收货地址失败");

    private CodeMsg( ) {
    }

    public CodeMsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
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

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
