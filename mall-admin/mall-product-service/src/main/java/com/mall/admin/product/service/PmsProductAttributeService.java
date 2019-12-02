package com.mall.admin.product.service;

import com.mall.admin.model.PmsProductAttribute;
import com.mall.admin.product.dto.PmsProductAttributeDTO;
import com.mall.admin.product.vo.PmsProductAttrInfoVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性Service
 * Created by macro on 2018/4/26.
 */
public interface PmsProductAttributeService {
    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性；2->参数
     * @return
     */
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(PmsProductAttributeDTO pmsProductAttributeDTO);

    /**
     * 修改商品属性
     */
    int update(Long id, PmsProductAttributeDTO pmsProductAttributeDTO);

    /**
     * 获取单个商品属性信息
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 批量删除 商品属性信息
     * @param ids
     * @return
     */
    @Transactional
    int delete(List<Long> ids);

    /**
     * 通过产品属性分类ID获取商品属性信息
     * @param productCategoryId
     * @return
     */
    List<PmsProductAttrInfoVO> getProductAttrInfo(Long productCategoryId);
}
