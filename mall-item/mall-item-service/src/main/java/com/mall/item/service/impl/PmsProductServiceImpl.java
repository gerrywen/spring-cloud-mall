package com.mall.item.service.impl;

import com.mall.item.mapper.PmsAppProductMapper;
import com.mall.item.service.PmsProductService;
import com.mall.item.vo.PmsCartProductVO;
import com.mall.item.vo.PmsPromotionProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * program: spring-cloud-mall->PmsProductServiceImpl
 * description:
 * author: gerry
 * created: 2019-12-18 22:59
 **/
@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    private PmsAppProductMapper pmsAppProductMapper;

    @Override
    public PmsCartProductVO getCartProduct(Long id) {
        return pmsAppProductMapper.getCartProduct(id);
    }

    @Override
    public List<PmsPromotionProductVO> getPromotionProductList(List<Long> ids) {
        return pmsAppProductMapper.getPromotionProductList(ids);
    }
}
