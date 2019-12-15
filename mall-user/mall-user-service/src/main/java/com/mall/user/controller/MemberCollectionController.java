package com.mall.user.controller;

import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.user.po.MemberProductCollection;
import com.mall.user.service.MemberCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收藏管理Controller
 * Created by macro on 2018/8/2.
 */
@RestController
@Api(value = "MemberCollectionController", tags = "member-会员收藏管理")
@RequestMapping("member/collection")
public class MemberCollectionController {
    @Autowired
    private MemberCollectionService memberCollectionService;

    @PostMapping("product/add")
    @ApiOperation("添加商品收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result addProduct(@RequestBody MemberProductCollection productCollection) {
        int count = memberCollectionService.addProduct(productCollection);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.error(CodeMsg.MEMBER_COLLECTION_PRODUCT_ERROR);
        }
    }

    @DeleteMapping("product/delete")
    @ApiOperation("删除收藏商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "member_id", value = "用户ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "product_id", value = "商品ID", required = true, dataType = "long", paramType = "query"),
    })
    public Result deleteProduct(@RequestParam("member_id") Long memberId, @RequestParam("product_id") Long productId) {
        int count = memberCollectionService.deleteProduct(memberId, productId);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.error(CodeMsg.MEMBER_CANCDL_COLLECTION_ERROR);
        }
    }

    @GetMapping("product/list/{member_id}")
    @ApiOperation("显示收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "member_id", value = "用户ID", required = true, dataType = "long", paramType = "query"),
    })
    public Result<List<MemberProductCollection>> listProduct(@PathVariable("member_id") Long memberId) {
        List<MemberProductCollection> memberProductCollectionList = memberCollectionService.listProduct(memberId);
        return Result.success(memberProductCollectionList);
    }
}
