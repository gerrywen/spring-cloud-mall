package com.mall.admin.oss.service;

import com.mall.admin.oss.vo.OssCallbackResultVO;
import com.mall.admin.oss.vo.OssPolicyResultVO;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * Created by macro on 2018/5/17.
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResultVO policy();
    /**
     * oss上传成功回调
     */
    OssCallbackResultVO callback(HttpServletRequest request);
}
