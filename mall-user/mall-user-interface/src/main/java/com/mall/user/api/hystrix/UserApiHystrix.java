package com.mall.user.api.hystrix;

import com.mall.admin.model.UmsIntegrationConsumeSetting;
import com.mall.admin.model.UmsMember;
import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.user.api.UserApi;
import com.mall.user.vo.SmsCouponHistoryDetailVO;

import java.util.List;

/**
 * program: spring-cloud-mall->UserApiHystrix
 * description: 用户接口熔断器
 * author: gerry
 * created: 2019-11-25 00:07
 **/
public class UserApiHystrix implements UserApi {
    @Override
    public UmsMember queryUser(String username, String password) {
        return null;
    }

    @Override
    public UmsIntegrationConsumeSetting getInfo() {
        return new UmsIntegrationConsumeSetting();
    }

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
