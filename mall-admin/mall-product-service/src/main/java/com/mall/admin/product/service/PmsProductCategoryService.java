package com.mall.admin.product.service;

import com.mall.admin.model.PmsProductCategory;
import com.mall.admin.product.dto.PmsProductCategoryDTO;
import com.mall.admin.product.vo.PmsProductCategoryWithChildrenItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsProductCategoryService
 * description: 产品分类Service
 * author: gerry
 * created: 2019-12-01 23:17
 **/
public interface PmsProductCategoryService {
    /**
     * 创建分类
     * @param pmsProductCategoryDTO
     * @return
     */
    @Transactional
    int create(PmsProductCategoryDTO pmsProductCategoryDTO);

    /**
     * 更新分类
     * @param id
     * @param pmsProductCategoryDTO
     * @return
     */
    @Transactional
    int update(Long id, PmsProductCategoryDTO pmsProductCategoryDTO);

    /**
     * 获取分页分类列表
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 获取分类信息
     * @param id
     * @return
     */
    PmsProductCategory getItem(Long id);

    /**
     * 批量更新 是否显示在导航栏
     * @param ids
     * @param navStatus
     * @return
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量更新 显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 获取分类下的所有子分类
     * @return
     */
    List<PmsProductCategoryWithChildrenItemVO> listWithChildren();
}
