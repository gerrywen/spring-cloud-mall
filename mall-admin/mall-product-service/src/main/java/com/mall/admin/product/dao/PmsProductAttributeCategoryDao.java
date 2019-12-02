package com.mall.admin.product.dao;

import com.mall.admin.product.vo.PmsProductAttributeCategoryItemVO;

import java.util.List;

/**
 * 自定义商品属性分类Dao
 * Created by macro on 2018/5/24.
 */
public interface PmsProductAttributeCategoryDao {
    List<PmsProductAttributeCategoryItemVO> getListWithAttr();
}
