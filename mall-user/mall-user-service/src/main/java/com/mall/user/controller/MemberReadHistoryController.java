package com.mall.user.controller;

import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.user.po.MemberReadHistory;
import com.mall.user.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 * Created by macro on 2018/8/3.
 */
@RestController
@Api(value = "MemberReadHistoryController", tags = "member-会员商品浏览记录管理")
@RequestMapping("member/history")
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @PostMapping("create")
    @ApiOperation("创建浏览记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.error(CodeMsg.MEMBER_CREATE_HISTORY_ERROR);
        }
    }

    @DeleteMapping("delete")
    @ApiOperation("删除浏览记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "ids", value = "浏览记录IDS", required = true, dataType = "array", paramType = "query"),
    })
    public Result delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return Result.success(count);
        } else {
            return Result.error(CodeMsg.MEMBER_DELETE_HISTORY_ERROR);
        }
    }

    @GetMapping("list")
    @ApiOperation("展示浏览记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "member_id", value = "用户ID", required = true, dataType = "long", paramType = "query"),
    })
    public Result<List<MemberReadHistory>> list(@RequestParam("member_id")  Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return Result.success(memberReadHistoryList);
    }
}
