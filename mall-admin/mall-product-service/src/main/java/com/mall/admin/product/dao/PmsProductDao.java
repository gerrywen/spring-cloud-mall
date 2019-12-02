package com.mall.admin.product.dao;

import com.mall.admin.product.vo.PmsProductVO;
import org.apache.ibatis.annotations.Param;


/**
 * 商品自定义Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductVO getUpdateInfo(@Param("id") Long id);
}
