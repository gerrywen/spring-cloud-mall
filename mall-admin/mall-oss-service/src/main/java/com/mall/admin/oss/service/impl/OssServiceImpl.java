package com.mall.admin.oss.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.mall.admin.oss.dto.OssCallbackDTO;
import com.mall.admin.oss.properties.OssProperties;
import com.mall.admin.oss.service.OssService;
import com.mall.admin.oss.vo.OssCallbackResultVO;
import com.mall.admin.oss.vo.OssPolicyResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * oss上传管理Service实现类
 * Created by macro on 2018/5/17.
 */
@Service
public class OssServiceImpl implements OssService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);

	@Autowired
	private static OssProperties ossProperties;

	@Autowired
	private OSSClient ossClient;

	/**
	 * 签名生成
	 */
	@Override
	public OssPolicyResultVO policy() {
		OssPolicyResultVO result = new OssPolicyResultVO();
		// 存储目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dir = ossProperties.getPrefix() + sdf.format(new Date());
		// 签名有效期
		long expireEndTime = System.currentTimeMillis() + ossProperties.getExpire() * 1000;
		Date expiration = new Date(expireEndTime);
		// 文件大小
		long maxSize = ossProperties.getMaxSize() * 1024 * 1024;
		// 回调
		OssCallbackDTO callback = new OssCallbackDTO();
		callback.setCallbackUrl(ossProperties.getCallback());
		callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
		callback.setCallbackBodyType("application/x-www-form-urlencoded");
		// 提交节点
		String action = "http://" + ossProperties.getBucketName() + "." + ossProperties.getEndpoint();
		try {
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
			String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String policy = BinaryUtil.toBase64String(binaryData);
			String signature = ossClient.calculatePostSignature(postPolicy);
			String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("utf-8"));
			// 返回结果
			result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
			result.setPolicy(policy);
			result.setSignature(signature);
			result.setDir(dir);
			result.setCallback(callbackData);
			result.setHost(action);
		} catch (Exception e) {
			LOGGER.error("签名生成失败", e);
		}
		return result;
	}

	@Override
	public OssCallbackResultVO callback(HttpServletRequest request) {
		OssCallbackResultVO result= new OssCallbackResultVO();
		String filename = request.getParameter("filename");
		filename = "http://".concat(ossProperties.getBucketName()).concat(".").concat(ossProperties.getEndpoint()).concat("/").concat(filename);
		result.setFilename(filename);
		result.setSize(request.getParameter("size"));
		result.setMimeType(request.getParameter("mimeType"));
		result.setWidth(request.getParameter("width"));
		result.setHeight(request.getParameter("height"));
		return result;
	}

}
