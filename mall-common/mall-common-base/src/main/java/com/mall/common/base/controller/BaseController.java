package com.mall.common.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * program: spring-cloud-mall->BaseController
 * description: 基础控制器
 * author: gerry
 * created: 2019-11-24 23:47
 **/
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
}
