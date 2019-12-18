package com.mall.item.mapper;

import com.mall.item.vo.PmsCartProductVO;
import com.mall.item.vo.PmsPromotionProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台系统自定义商品mapper
 * Created by macro on 2018/8/2.
 */
public interface PmsAppProductMapper {
    /**
     * 获取购物车需要的商品薪资
     * @param id
     * @return
     */
    PmsCartProductVO getCartProduct(@Param("id") Long id);

    /**
     * 获取促销商品列表
     * @param ids
     * @return
     */
    List<PmsPromotionProductVO> getPromotionProductList(@Param("ids") List<Long> ids);
}
