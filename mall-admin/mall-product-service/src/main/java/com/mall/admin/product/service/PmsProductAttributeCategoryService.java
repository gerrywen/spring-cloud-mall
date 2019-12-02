package com.mall.admin.product.service;

import com.mall.admin.model.PmsProductAttributeCategory;
import com.mall.admin.product.vo.PmsProductAttributeCategoryItemVO;

import java.util.List;

/**
 * 商品属性分类Service
 * Created by macro on 2018/4/26.
 */
public interface PmsProductAttributeCategoryService {
    /**
     * 创建产品属性分类表
     * @param name
     * @return
     */
    int create(String name);

    /**
     * 更新产品属性分类表
     * @param id
     * @param name
     * @return
     */
    int update(Long id, String name);

    /**
     * 删除 产品属性分类表
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 获取 单个产品属性分类
     * @param id
     * @return
     */
    PmsProductAttributeCategory getItem(Long id);

    /**
     * 获取 产品属性分类表
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 获取 产品属性分类下的所有商品属性参数表
     * @return
     */
    List<PmsProductAttributeCategoryItemVO> getListWithAttr();
}
