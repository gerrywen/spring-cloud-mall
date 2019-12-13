package com.mall.item.service;

import com.mall.item.vo.HomeContentResultVO;

/**
 * program: spring-cloud-mall->HomeService
 * description: 首页内容管理Service
 * author: gerry
 * created: 2019-12-13 21:38
 **/
public interface HomeService {

    /**
     * 获取首页内容
     */
    HomeContentResultVO content();

}
