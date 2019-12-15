package com.mall.user.controller;

import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.user.po.MemberBrandAttention;
import com.mall.user.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员关注品牌管理Controller
 * Created by macro on 2018/8/2.
 */
@RestController
@Api(value = "MemberAttentionController", tags = "member-会员关注品牌管理")
@RequestMapping("member/attention")
public class MemberAttentionController {
    @Autowired
    private MemberAttentionService memberAttentionService;


    @PostMapping("add")
    @ApiOperation("添加品牌关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        if(count>0){
            return Result.success(count);
        }else{
            return Result.error(CodeMsg.MEMBER_FOLLOW_BRAND_ERROR);
        }
    }

    @DeleteMapping("delete")
    @ApiOperation("取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "member_id", value = "用户ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "brand_id", value = "品牌ID", required = true, dataType = "long", paramType = "query"),
    })
    public Result delete(@RequestParam("member_id") Long memberId, @RequestParam("brand_id") Long brandId) {
        int count = memberAttentionService.delete(memberId,brandId);
        if(count>0){
            return Result.success(count);
        }else{
            return Result.error(CodeMsg.MEMBER_CANCDL_FOLLOW_ERROR);
        }
    }

    @GetMapping("list/{member_id}")
    @ApiOperation("显示关注列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "member_id", value = "用户ID", required = true, dataType = "long", paramType = "query"),
    })
    public Result<List<MemberBrandAttention>> list(@PathVariable("member_id") Long memberId) {
        List<MemberBrandAttention> memberBrandAttentionList = memberAttentionService.list(memberId);
        return Result.success(memberBrandAttentionList);
    }
}
