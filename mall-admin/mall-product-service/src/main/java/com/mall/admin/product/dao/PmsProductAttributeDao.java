package com.mall.admin.product.dao;

import com.mall.admin.product.vo.PmsProductAttrInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品属性Dao
 * Created by macro on 2018/5/23.
 */
public interface PmsProductAttributeDao {
    List<PmsProductAttrInfoVO> getProductAttrInfo(@Param("id") Long productCategoryId);
}
