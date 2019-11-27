package com.mall.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * program: spring-cloud-mall->UploadService
 * description: 上传接口类
 * author: gerry
 * created: 2019-11-28 00:40
 **/
public interface UploadService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
