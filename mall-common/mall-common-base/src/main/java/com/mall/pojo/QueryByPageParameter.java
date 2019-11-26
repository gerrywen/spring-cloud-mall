package com.mall.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * program: spring-cloud-mall->BrandQueryByPageParameter
 * description: 品牌分页查询
 * author: gerry
 * created: 2019-11-23 10:42
 **/
@ApiModel(value="QueryByPageParameter",description="基类分页DTO")
@Data
public class QueryByPageParameter {

    /**
     *  - page：当前页，int
     *  - rows：每页大小，int
     *  - sortBy：排序字段，String
     *  - desc：是否为降序，boolean
     *  - key：搜索关键词，String
     */
    @ApiModelProperty(value="页码",name="page",example="1")
    private Integer page = 1;

    @ApiModelProperty(value="每页条数",name="rows",example="10")
    private Integer rows = 10;

    @ApiModelProperty(value="排序字段",name="sort_by",example="id")
    private String sortBy;

    @ApiModelProperty(value="是否为降序",name="sort_by",example="false/true")
    private Boolean desc = false;

    @ApiModelProperty(value="关键词",name="key")
    private String key;


    public QueryByPageParameter(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        this.page = page;
        this.rows = rows;
        this.sortBy = sortBy;
        this.desc = desc;
        this.key = key;
    }

    public QueryByPageParameter(){
        super();
    }

    @Override
    public String toString() {
        return "BrandQueryByPageParameter{" +
                "page=" + page +
                ", rows=" + rows +
                ", sortBy='" + sortBy + '\'' +
                ", desc=" + desc +
                ", key='" + key + '\'' +
                '}';
    }
}
