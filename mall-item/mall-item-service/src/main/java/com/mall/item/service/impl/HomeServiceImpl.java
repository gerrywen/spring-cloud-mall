package com.mall.item.service.impl;

import com.mall.item.service.HomeService;
import com.mall.item.vo.HomeContentResultVO;
import org.springframework.stereotype.Service;

/**
 * program: spring-cloud-mall->HomeServiceImpl
 * description:
 * author: gerry
 * created: 2019-12-13 21:39
 **/
@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public HomeContentResultVO content() {
        HomeContentResultVO homeContentResultVO = new HomeContentResultVO();



        return homeContentResultVO;
    }
}
