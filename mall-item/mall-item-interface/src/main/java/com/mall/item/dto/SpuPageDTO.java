package com.mall.item.dto;

import com.mall.parameter.pojo.QueryByPageParameter;
import io.swagger.annotations.ApiModel;

/**
 * @author wenguoli
 * @date 2019/11/26 10:30
 */
@ApiModel(value="SpuPageDTO",description="Spu分页DTO")
public class SpuPageDTO extends QueryByPageParameter {
    /**
     *         - page：当前页，int
     *         - rows：每页大小，int
     *         - sortBy：排序字段，String
     *         - desc：是否为降序，boolean
     *         - key：搜索关键词，String
     *         - saleable: 是否上下架
     */
    private Boolean saleable;

    public Boolean getSaleable() {
        return saleable;
    }

    public void setSaleable(Boolean saleable) {
        this.saleable = saleable;
    }

    public SpuPageDTO(Integer page, Integer rows, String sortBy, Boolean desc, String key, Boolean saleable) {
        super(page, rows, sortBy, desc, key);
        this.saleable = saleable;
    }

    public SpuPageDTO(Boolean saleable) {
        this.saleable = saleable;
    }

    @Override
    public String toString() {
        return "SpuQueryByPageParameter{" +
                "saleable=" + saleable +
                '}';
    }
}
