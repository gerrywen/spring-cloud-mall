package com.mall.admin.search.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 搜索中的商品属性信息
 * @author wenguoli
 * @date 2019/12/6 15:50
 */
@Data
@ApiModel(value = "elk-存储产品参数信息的表")
public class EsProductAttributeValue implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    //属性值
    @ApiModelProperty(value = "属性值")
    @Field(type = FieldType.Keyword)
    private String value;

    @ApiModelProperty(value = "属性主键id")
    private Long productAttributeId;

    // pms_product_attribute 属性名称
    @ApiModelProperty(value = "属性名称")
    @Field(type=FieldType.Keyword)
    private String name;

    @ApiModelProperty(value = "属性的类型；0->规格；1->参数")
    //属性参数：0->规格；1->参数
    private Integer type;
}
