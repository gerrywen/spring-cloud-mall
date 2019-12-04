package com.mall.admin.order.controller;

import com.mall.admin.base.api.CommonPage;
import com.mall.admin.base.api.CommonResult;
import com.mall.admin.model.OmsOrderReturnApply;
import com.mall.admin.order.dto.OmsReturnApplyQueryDTO;
import com.mall.admin.order.dto.OmsUpdateStatusDTO;
import com.mall.admin.order.service.OmsOrderReturnApplyService;
import com.mall.admin.order.vo.OmsOrderReturnApplyResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退货申请管理
 * Created by macro on 2018/10/18.
 */
@RestController
@Api(value = "OmsOrderReturnApplyController", tags = "marketing-订单退货申请管理")
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryDTO queryParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApply> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(returnApplyList));
    }

    @ApiOperation("批量删除申请")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResultVO result = returnApplyService.getItem(id);
        return CommonResult.success(result);
    }

    @ApiOperation("修改申请状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusDTO statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
