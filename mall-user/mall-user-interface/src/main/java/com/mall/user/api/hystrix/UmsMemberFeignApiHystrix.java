package com.mall.user.api.hystrix;

import com.mall.admin.model.UmsMember;
import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.user.api.UmsMemberFeignApi;
import com.mall.user.vo.SmsCouponHistoryDetailVO;

import java.util.List;

/**
 * program: spring-cloud-mall->UmsMemberApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-16 21:29
 **/
public class UmsMemberFeignApiHystrix implements UmsMemberFeignApi {
    @Override
    public void updateIntegration(Integer integration) {

    }

    @Override
    public UmsMember getUserInfo() {
        return null;
    }

    @Override
    public List<SmsCouponHistoryDetailVO> listCart(Integer type) {
        return null;
    }

    @Override
    public List<UmsMemberReceiveAddress> list() {
        return null;
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        return null;
    }
}
