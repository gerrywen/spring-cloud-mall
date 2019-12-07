package com.mall.admin.search.dao;

import com.mall.admin.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * @author wenguoli
 * @date 2019/12/6 14:38
 */
public interface EsProductDao {
    /**
     * 获取elk所有商品信息列表
     * @param id
     * @return
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);

}
