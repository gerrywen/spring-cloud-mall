package com.mall.item.dto;

import com.mall.parameter.pojo.QueryByPageParameter;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PageDTO",description="分页DTO")
public class PageDTO extends QueryByPageParameter {

    /**
     *  - page：当前页，int
     *  - rows：每页大小，int
     *  - sortBy：排序字段，String
     *  - desc：是否为降序，boolean
     *  - key：搜索关键词，String
     */

    public PageDTO(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        super(page, rows, sortBy, desc, key);
    }

    public PageDTO(){
        super();
    }
}
