//package com.mall.admin.subject.api;
//
//import com.mall.admin.model.CmsSubjectProductRelation;
//import com.mall.admin.subject.api.hystrix.CmsSubjectApiHystrix;
//import com.mall.common.base.config.FeignConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
///**
// * program: spring-cloud-mall->CmsSubjectApi
// * description: 专题接口
// * author: gerry
// * created: 2019-12-03 22:38
// **/
//@FeignClient(value = "mall-admin", fallback = CmsSubjectApiHystrix.class, configuration = FeignConfig.class)
//@RequestMapping("/subject")
//public interface CmsSubjectApi {
//
//    /**
//     * 插入专题商品
//     * @param subjectProductRelationList
//     * @return
//     */
//    @PostMapping("/insertProduct")
//    int insertList(List<CmsSubjectProductRelation> subjectProductRelationList);
//}
