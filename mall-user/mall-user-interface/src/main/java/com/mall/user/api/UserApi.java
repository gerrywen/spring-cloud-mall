package com.mall.user.api;

import com.mall.admin.model.UmsIntegrationConsumeSetting;
import com.mall.admin.model.UmsMember;
import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.hystrix.UserApiHystrix;
import com.mall.user.vo.SmsCouponHistoryDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * program: spring-cloud-mall->UserApi
 * description: 用户接口
 * author: gerry
 * created: 2019-11-25 00:05
 **/
//@FeignClient(value = "mall-user-service", fallback = UserApiHystrix.class, configuration = FeignConfig.class)
public interface UserApi {
    /**
     * 用户验证
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    UmsMember queryUser(@RequestParam("username")String username, @RequestParam("password")String password);

    @GetMapping("integration/setting/internal/info")
    UmsIntegrationConsumeSetting getInfo();

    /**
     * 根据会员id修改会员积分
     */
    @PutMapping("internal/integration")
    void updateIntegration(@RequestParam("integration") Integer integration);


    /**
     * 根据会员编号获取会员
     */
    @GetMapping("info")
    UmsMember getUserInfo();


    @GetMapping(value = "member/coupon/list/cart/{type}")
    List<SmsCouponHistoryDetailVO> listCart(@PathVariable("type") Integer type);


    @GetMapping("member/address/list")
    List<UmsMemberReceiveAddress> list();

    @GetMapping("member/address/{id}")
    UmsMemberReceiveAddress getItem(Long id);


}
