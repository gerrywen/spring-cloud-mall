package com.mall.user.api;

import com.mall.admin.model.UmsMember;
import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.common.base.config.FeignConfig;
import com.mall.user.api.hystrix.UmsMemberFeignApiHystrix;
import com.mall.user.vo.SmsCouponHistoryDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * program: spring-cloud-mall->UmsMemberApi
 * description:
 * author: gerry
 * created: 2019-12-16 21:28
 **/
@FeignClient(value = "mall-service", fallback = UmsMemberFeignApiHystrix.class, configuration = FeignConfig.class)
public interface UmsMemberFeignApi {
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
