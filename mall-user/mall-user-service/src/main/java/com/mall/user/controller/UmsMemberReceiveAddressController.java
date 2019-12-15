package com.mall.user.controller;

import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.user.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收货地址管理Controller
 * Created by macro on 2018/8/28.
 */
@RestController
@Api(value = "UmsMemberReceiveAddressController", tags = "UmsMember-会员收货地址管理")
@RequestMapping("member/address")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;

    @PostMapping("add")
    @ApiOperation("添加收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result add(@RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.add(address);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.MEMBER_ADDRESS_ADD_ERROR);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result delete(@PathVariable Long id) {
        int count = memberReceiveAddressService.delete(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.MEMBER_ADDRESS_DELETE_ERROR);
    }

    @PostMapping("update/{id}")
    @ApiOperation("修改收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result update(@PathVariable Long id, @RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.update(id, address);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.MEMBER_ADDRESS_UPDATE_ERROR);
    }

    @GetMapping("list")
    @ApiOperation("显示所有收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result<List<UmsMemberReceiveAddress>> list() {
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.list();
        return Result.success(addressList);
    }

    @GetMapping("{id}")
    @ApiOperation("显示收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer token", required = true, dataType = "string", paramType = "header"),
    })
    public Result<UmsMemberReceiveAddress> getItem(@PathVariable Long id) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getItem(id);
        return Result.success(address);
    }
}
