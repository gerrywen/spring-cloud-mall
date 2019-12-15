package com.mall.item.api;

import com.mall.common.base.config.FeignConfig;
import com.mall.common.base.pojo.PageResult;
import com.mall.common.base.response.Result;
import com.mall.item.api.hystrix.SpuApiHystrix;
import com.mall.item.bo.SpuBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * program: spring-cloud-mall->SpuApi
 * description:
 * author: gerry
 * created: 2019-12-14 20:37
 **/
@FeignClient(value = "mall-item", fallback = SpuApiHystrix.class, configuration = FeignConfig.class)
public interface SpuApi {
    /**
     * 分页查询
     * @param page
     * @param rows
     * @param desc
     * @param saleable
     * @return
     */
    @GetMapping("spu/page")
    Result<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable);
}
