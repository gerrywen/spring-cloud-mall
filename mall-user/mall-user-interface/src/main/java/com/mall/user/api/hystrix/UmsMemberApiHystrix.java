package com.mall.user.api.hystrix;

import com.mall.admin.model.UmsMember;
import com.mall.user.api.UmsMemberApi;

/**
 * program: spring-cloud-mall->UmsMemberApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-16 21:29
 **/
public class UmsMemberApiHystrix implements UmsMemberApi {
    @Override
    public void updateIntegration(Integer integration) {

    }

    @Override
    public UmsMember getUserInfo() {
        return null;
    }
}
