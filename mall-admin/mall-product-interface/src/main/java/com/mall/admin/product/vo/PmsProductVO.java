package com.mall.admin.product.vo;

import com.mall.admin.product.dto.PmsProductDTO;
import lombok.Data;

/**
 * 查询单个产品进行修改时返回的结果
 * Created by macro on 2018/4/26.
 */
@Data
public class PmsProductVO extends PmsProductDTO {
    //商品所选分类的父id
    private Long cateParentId;

    public Long getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(Long cateParentId) {
        this.cateParentId = cateParentId;
    }
}
