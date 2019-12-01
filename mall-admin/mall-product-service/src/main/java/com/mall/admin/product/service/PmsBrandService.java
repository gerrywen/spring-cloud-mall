package com.mall.admin.product.service;

import com.mall.admin.model.PmsBrand;
import com.mall.admin.product.dto.PmsBrandDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsBrandService
 * description: 商品品牌Service
 * author: gerry
 * created: 2019-12-01 22:44
 **/
public interface PmsBrandService {
    /**
     * 查询所有品牌列表
     * @return
     */
    List<PmsBrand> listAllBrand();

    /**
     * 创建品牌
     * @param pmsBrandDTO
     * @return
     */
    int createBrand(PmsBrandDTO pmsBrandDTO);

    /**
     * 更新品牌
     * @param id
     * @param pmsBrandDTO
     * @return
     */
    @Transactional
    int updateBrand(Long id, PmsBrandDTO pmsBrandDTO);

    /**
     * 删除单个品牌
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 删除多个品牌
     * @param ids
     * @return
     */
    int deleteBrand(List<Long> ids);

    /**
     * 品牌分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * 根据品牌ID获取品牌信息
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);

    /**
     * 批量更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 批量更新是否为品牌制造商
     * @param ids
     * @param factoryStatus
     * @return
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
