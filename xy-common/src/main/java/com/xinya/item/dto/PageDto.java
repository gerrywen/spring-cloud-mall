package com.xinya.item.dto;

import lombok.Data;

@Data
public class PageDto {
    /**
     *  - page：当前页，int
     *  - rows：每页大小，int
     *  - sortBy：排序字段，String
     *  - desc：是否为降序，boolean
     *  - key：搜索关键词，String
     */

    private Integer page;
    private Integer rows;
    private String sortBy;
    private Boolean desc;
    private String key;

    public PageDto(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        this.page = page;
        this.rows = rows;
        this.sortBy = sortBy;
        this.desc = desc;
        this.key = key;
    }

    public PageDto(){
        super();
    }
}
