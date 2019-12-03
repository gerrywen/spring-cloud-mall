package com.mall.admin.subject.service;

import com.mall.admin.model.CmsPrefrenceArea;
import com.mall.admin.model.CmsPrefrenceAreaProductRelation;

import java.util.List;

/**
 * 优选专区Service
 * Created by macro on 2018/6/1.
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取优选专区
     * @return
     */
    List<CmsPrefrenceArea> listAll();

    /**
     * 插入 优选专区和产品关系表
     * @param prefrenceAreaProductRelationList
     * @return
     */
    int insertList(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}
