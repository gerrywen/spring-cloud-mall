package com.mall.item.controller;

import com.mall.common.base.response.Result;
import com.mall.item.service.HomeService;
import com.mall.item.vo.HomeContentResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * program: spring-cloud-mall->HomeController
 * description: 首页数据
 * author: gerry
 * created: 2019-12-13 21:21
 **/
@RestController
@Api(value = "PmsHomeController", tags = "item-首页内容管理")
@RequestMapping("/home")
public class PmsHomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(value = "/content")
    @ApiOperation(value = "获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticket", value = "token", required = false, dataType = "string", paramType = "header")
    })
    public Result<HomeContentResultVO> content() {
        HomeContentResultVO content = homeService.content();
        return Result.success(content);
    }


}

