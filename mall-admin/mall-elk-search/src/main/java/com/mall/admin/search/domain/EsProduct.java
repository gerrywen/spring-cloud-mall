package com.mall.admin.search.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索中的商品信息
 *
 * https://www.cnblogs.com/cjsblog/p/10328367.html
 *
 * shards 一个索引应该有的主分片（primary shards）数。默认是5。而且，只能在索引创建的时候设置。
 *
 * replicas 每个主分片所拥有的副本数，默认是1。
 *
 * index.refresh_interval  ：多久执行一次刷新操作，使得最近的索引更改对搜索可见。默认是1秒。设置为-1表示禁止刷新。
 *
 * indexStoreType 可选的存储类型有：
 *      fs  ：默认实现，取决于操作系统
 *      simplefs  ：对应Lucene SimpleFsDirectory
 *      niofs  ：对应Lucene NIOFSDirectory
 *      mmapfs  ：对应Lucene MMapDirectory
 *
 * @author wenguoli
 * @date 2019/12/6 14:39
 */
@Document(indexName = "pms", type = "product", shards = 5, replicas = 1, refreshInterval = "-1")
@Data
@ApiModel(value = "elk-商品表")
public class EsProduct implements Serializable {

    @ApiModelProperty(value = "主键id")
    @Id
    private Long id;

    @ApiModelProperty(value = "商品货号")
    @Field(type = FieldType.Keyword)
    private String productSn;

    @ApiModelProperty(value = "商品品牌id")
    private Long brandId;

    @ApiModelProperty(value = "商品品牌名称")
    @Field(type = FieldType.Keyword)
    private String brandName;

    @ApiModelProperty(value = "商品分类id")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品分类名称")
    @Field(type = FieldType.Keyword)
    private String productCategoryName;

    @ApiModelProperty(value = "商品首图")
    private String pic;

    @ApiModelProperty(value = "商品名称")
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;

    @ApiModelProperty(value = "商品副标题")
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String subTitle;

    @ApiModelProperty(value = "商品关键词")
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品销量")
    private Integer sale;

    @ApiModelProperty(value = "新品状态:0->不是新品；1->新品")
    private Integer newStatus;

    @ApiModelProperty(value = "推荐状态；0->不推荐；1->推荐")
    private Integer recommandStatus;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    private Integer promotionType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "商品属性值列表")
    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;

}
