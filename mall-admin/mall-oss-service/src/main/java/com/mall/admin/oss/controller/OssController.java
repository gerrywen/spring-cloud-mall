package com.mall.admin.oss.controller;


import com.mall.admin.base.api.CommonResult;
import com.mall.admin.oss.service.OssService;
import com.mall.admin.oss.vo.OssCallbackResultVO;
import com.mall.admin.oss.vo.OssPolicyResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss相关操作接口
 * Created by macro on 2018/4/26.
 */
@RestController
@Api(value = "CmsPrefrenceAreaController", tags = "oss-上传管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResultVO> policy() {
        OssPolicyResultVO result = ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResultVO> callback(HttpServletRequest request) {
        OssCallbackResultVO ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

}
