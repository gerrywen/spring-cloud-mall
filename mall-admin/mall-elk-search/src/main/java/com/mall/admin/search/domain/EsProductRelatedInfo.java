package com.mall.admin.search.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 搜索相关商品品牌名称，分类名称及属性
 * @author wenguoli
 * @date 2019/12/6 15:51
 */
@Data
@ApiModel(value = "elk-商品相关信息")
public class EsProductRelatedInfo {
    @ApiModelProperty(value = "品牌名称列表")
    private List<String> brandNames;

    @ApiModelProperty(value = "分类名称列表")
    private List<String> productCategoryNames;

    @ApiModelProperty(value = "商品属性列表")
    private List<ProductAttr> productAttrs;

}
