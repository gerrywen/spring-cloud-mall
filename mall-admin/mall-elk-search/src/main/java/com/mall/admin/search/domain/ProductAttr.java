package com.mall.admin.search.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 关联商品属性信息
 * @author wenguoli
 * @date 2019/12/6 15:51
 */
@Data
@ApiModel(value = "elk-商品属性参数")
public class ProductAttr {

    // pms_product_attribute
    @ApiModelProperty(value = "属性id")
    private Long attrId;

    // pms_product_attribute
    @ApiModelProperty(value = "属性名称")
    private String attrName;

    // pms_product_attribute_value
    @ApiModelProperty(value = "存储产品参数信息的所有值")
    private List<String> attrValues;
}
