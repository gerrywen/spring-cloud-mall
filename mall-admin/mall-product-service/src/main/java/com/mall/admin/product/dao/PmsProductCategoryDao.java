package com.mall.admin.product.dao;

import com.mall.admin.product.vo.PmsProductCategoryWithChildrenItemVO;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsProductCategoryDao
 * description: 商品分类自定义Dao
 * author: gerry
 * created: 2019-12-01 23:17
 **/
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItemVO> listWithChildren();
}
