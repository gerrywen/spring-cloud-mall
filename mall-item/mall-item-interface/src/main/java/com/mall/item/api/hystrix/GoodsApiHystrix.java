package com.mall.item.api.hystrix;

import com.mall.common.base.pojo.PageResult;
import com.mall.item.api.GoodsApi;
import com.mall.item.bo.SpuBo;
import com.mall.item.pojo.SeckillGoods;
import com.mall.item.pojo.Sku;
import com.mall.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * program: spring-cloud-mall->GoodsApiHystrix
 * description:
 * author: gerry
 * created: 2019-12-14 08:06
 **/
@Component
public class GoodsApiHystrix implements GoodsApi {
    @Override
    public PageResult<SpuBo> querySpuByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key, Boolean saleable) {
        return null;
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long id) {
        return null;
    }

    @Override
    public List<Sku> querySkuBySpuId(Long id) {
        return null;
    }

    @Override
    public SpuBo queryGoodsById(Long id) {
        return null;
    }

    @Override
    public Sku querySkuById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<SeckillGoods>> querySeckillGoods() {
        return null;
    }
}
