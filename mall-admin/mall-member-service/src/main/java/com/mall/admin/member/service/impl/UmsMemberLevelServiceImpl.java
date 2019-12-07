package com.mall.admin.member.service.impl;

import com.mall.admin.mapper.UmsMemberLevelMapper;
import com.mall.admin.member.service.UmsMemberLevelService;
import com.mall.admin.model.UmsMemberLevel;
import com.mall.admin.model.UmsMemberLevelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * program: spring-cloud-mall->UmsMemberLevelServiceImpl
 * description: 会员等级管理Service实现类
 * author: gerry
 * created: 2019-12-07 21:38
 **/
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
