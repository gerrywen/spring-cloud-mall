package com.mall.item.controller;

import com.mall.common.base.pojo.PageResult;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.common.redis.constant.RedisKey;
import com.mall.common.redis.enums.CtimsModelEnum;
import com.mall.common.redis.utils.CommonRedisUtils;
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

import java.util.List;

@RestController
@RequestMapping("brand")
@Api(value = "BrandController", tags = "品牌")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CommonRedisUtils redisUtils;

    /**
     * 分页查询品牌
     * @param pageDTO 分页
     * @return
     */
    @GetMapping("page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = false, dataType = "string", paramType = "header"),
    })
    public Result<PageResult<Brand>> queryBrandByPage(PageDTO pageDTO){
        PageResult<Brand> result = this.brandService.queryBrandByPage(pageDTO);
        if(result == null){ // HttpStatus.NOT_FOUND
            return Result.error(CodeMsg.BRAND_NOT_EXIST);
        }
        return Result.success(result);
    }

    /**
     * 品牌新增
     * @param brand
     * @param categories
     * @return
     */
    @PostMapping
    public ResponseEntity<Void>  saveBrand(Brand brand, @RequestParam("categories") List<Long> categories){
        System.out.println(brand);
        this.brandService.saveBrand(brand, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 品牌修改
     * @param brand
     * @param categories
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand,@RequestParam("categories") List<Long> categories){
        this.brandService.updateBrand(brand,categories);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除tb_category_brand中的数据
     * @param bid
     * @return
     */
    @DeleteMapping("cid_bid/{bid}")
    public ResponseEntity<Void> deleteByBrandIdInCategoryBrand(@PathVariable("bid") Long bid){
        //System.out.println("删除中间表");
        this.brandService.deleteByBrandIdInCategoryBrand(bid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    /**
     * 删除tb_brand中的数据,单个删除、多个删除二合一
     * @param bid
     * @return
     */
    @DeleteMapping("bid/{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("bid") String bid){
        String separator="-";
        if(bid.contains(separator)){
            String[] ids=bid.split(separator);
            for (String id:ids){
                this.brandService.deleteBrand(Long.parseLong(id));
            }
        }
        else {
            this.brandService.deleteBrand(Long.parseLong(bid));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据category的id查询品牌信息
     * @param cid
     * @return
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategoryId(@PathVariable("cid") Long cid){
        List<Brand> list = this.brandService.queryBrandByCategoryId(cid);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据品牌id结合，查询品牌信息
     * @param ids
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        List<Brand> list = this.brandService.queryBrandByBrandIds(ids);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/index")
    public String index() {
        // 存储数据
        String key = RedisKey.STRING_ACCESS_TOKEN;
        redisUtils.set(CtimsModelEnum.CTIMS_COMM_CAP, key, "value1value1value1value1value1", 7200);
        // 获取数据
        String val =  redisUtils.get(CtimsModelEnum.CTIMS_COMM_CAP, key);
        System.out.println("val = " + val);
        return "brand index：" + val;
    }

}
