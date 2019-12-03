package com.mall.admin.subject.service;

import com.mall.admin.model.CmsSubject;
import com.mall.admin.model.CmsSubjectProductRelation;

import java.util.List;

/**
 * 商品专题Service
 * Created by macro on 2018/6/1.
 */
public interface CmsSubjectService {
    /**
     * 查询所有专题
     */
    List<CmsSubject> listAll();

    /**
     * 分页查询专题
     */
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 插入专题商品
     * @param subjectProductRelationList
     * @return
     */
    int insertList(List<CmsSubjectProductRelation> subjectProductRelationList);
}
