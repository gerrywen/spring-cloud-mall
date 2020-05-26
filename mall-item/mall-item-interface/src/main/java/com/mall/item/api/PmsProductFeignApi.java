package com.mall.item.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.common.base.pojo.PageResult;
import com.mall.item.api.hystrix.PmsProductFeignApiHystrix;
import com.mall.item.bo.SpuBo;
import com.mall.item.pojo.SeckillGoods;
import com.mall.item.pojo.Sku;
import com.mall.item.pojo.SpuDetail;
import com.mall.item.vo.PmsCartProductVO;
import com.mall.item.vo.PmsPromotionProductVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * program: spring-cloud-mall->GoodsApi
 * description:
 * author: gerry
 * created: 2019-12-14 08:02
 **/
@FeignClient(value = "mall-item-service", fallback = PmsProductFeignApiHystrix.class, configuration = FeignConfig.class)
public interface PmsProductFeignApi {

    /**
     * 获取购物车需要的商品信息
     * @param id
     * @return
     */
    @GetMapping("product/internal/cart/{id}")
    PmsCartProductVO getCartProduct(@PathVariable("id") Long id);

    /**
     * 获取促销商品列表
     * @param ids
     * @return
     */
    @GetMapping("product/internal/promotion")
    List<PmsPromotionProductVO> getPromotionProductList(@RequestParam("ids") List<Long> ids);

    /**
     * 分页查询
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @param saleable
     * @return
     */
    @GetMapping("goods/spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable);
    /**
     * 根据spu商品id查询详情
     * @param id
     * @return
     */
    @GetMapping("goods/spu/detail/{id}")
    SpuDetail querySpuDetailBySpuId(@PathVariable("id") Long id);

    /**
     * 根据Spu的id查询其下所有的sku
     * @param id
     * @return
     */
    @GetMapping("goods/sku/list/{id}")
    List<Sku> querySkuBySpuId(@PathVariable("id") Long id);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @GetMapping("goods/spu/{id}")
    SpuBo queryGoodsById(@PathVariable("id") Long id);

    /**
     * 根据sku的id查询sku
     * @param id
     * @return
     */
    @GetMapping("goods/sku/{id}")
    Sku querySkuById(@PathVariable("id") Long id);


    /**
     * 查询秒杀商品
     * @return
     */
    @GetMapping("goods/seckill/list")
    ResponseEntity<List<SeckillGoods>> querySeckillGoods();

}
