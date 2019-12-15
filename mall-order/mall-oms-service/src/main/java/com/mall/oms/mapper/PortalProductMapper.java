package com.mall.oms.mapper;

import com.mall.oms.vo.CartProductVO;
import com.mall.oms.vo.PromotionProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台系统自定义商品Dao
 * Created by macro on 2018/8/2.
 */
public interface PortalProductMapper {
    CartProductVO getCartProduct(@Param("id") Long id);

    List<PromotionProductVO> getPromotionProductList(@Param("ids") List<Long> ids);
}
