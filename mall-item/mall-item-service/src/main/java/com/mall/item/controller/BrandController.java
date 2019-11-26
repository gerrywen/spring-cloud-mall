package com.mall.item.controller;

import com.mall.common.pojo.PageResult;
import com.mall.item.dto.PageDTO;
import com.mall.item.pojo.Brand;
import com.mall.item.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
@Api(value = "BrandController", tags = "品牌")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 分页查询品牌
     * @param pageDTO 分页
     * @return
     */
    @GetMapping("page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = false, dataType = "string", paramType = "header"),
    })
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(PageDTO pageDTO){
        PageResult<Brand> result = this.brandService.queryBrandByPage(pageDTO);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/index")
    public String index() {
        return "brand index";
    }

    @GetMapping("/info")
    public String info() {
        return "brand info";
    }

    @GetMapping("/{id}")
    public String queryById(@PathVariable("id") Long id) {
        return "id:" + id;
    }

}
