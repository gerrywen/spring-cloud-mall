package com.mall.admin.product.dao;

import com.mall.admin.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsProductCategoryAttributeRelationDao
 * description: 自定义商品分类和属性关系Dao
 * author: gerry
 * created: 2019-12-01 23:22
 **/
public interface PmsProductCategoryAttributeRelationDao {
    /**
     * 分类相关属性
     * @param productCategoryAttributeRelationList
     * @return
     */
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}
