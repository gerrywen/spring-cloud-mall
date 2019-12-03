//package com.mall.admin.subject.api;
//
//import com.mall.admin.model.CmsPrefrenceAreaProductRelation;
//import com.mall.admin.subject.api.hystrix.CmsPrefrenceAreaHystrix;
//import com.mall.common.base.config.FeignConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
///**
// * program: spring-cloud-mall->CmsPrefrenceArea
// * description: 优选专区
// * author: gerry
// * created: 2019-12-03 22:42
// **/
//@FeignClient(value = "mall-admin", fallback = CmsPrefrenceAreaHystrix.class, configuration = FeignConfig.class)
//@RequestMapping("/prefrenceArea")
//public interface CmsPrefrenceAreaApi {
//    /**
//     * 插入 优选专区和产品关系表
//     * @param prefrenceAreaProductRelationList
//     * @return
//     */
//    @PostMapping("/insertProduct")
//    int insertList(List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
//}
