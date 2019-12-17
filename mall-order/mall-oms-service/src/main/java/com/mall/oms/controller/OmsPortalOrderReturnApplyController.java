package com.mall.oms.controller;

import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import com.mall.oms.dto.OmsOrderReturnApplyParamDTO;
import com.mall.oms.service.OmsPortalOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 申请退货管理Controller
 * Created by macro on 2018/10/17.
 */
@RestController
@Api(value = "OmsPortalOrderReturnApplyController", tags = "order-申请退货管理")
@RequestMapping("return/apply")
public class OmsPortalOrderReturnApplyController {
    @Autowired
    private OmsPortalOrderReturnApplyService returnApplyService;

    @ApiOperation("申请退货")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody OmsOrderReturnApplyParamDTO returnApply) {
        int count = returnApplyService.create(returnApply);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.error(CodeMsg.ORDER_RETURN_CREATE_ERROR);
    }
}
