package com.mall.admin.member.service;

import com.mall.admin.model.UmsMemberLevel;

import java.util.List;

/**
 * program: spring-cloud-mall->UmsMemberLevelService
 * description: 会员等级管理Service
 * author: gerry
 * created: 2019-12-07 21:37
 **/
public interface UmsMemberLevelService {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
