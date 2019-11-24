package com.mall.item.bo;

import com.mall.item.pojo.Sku;
import com.mall.item.pojo.Spu;
import com.mall.item.pojo.SpuDetail;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * program: spring-cloud-mall->SpuBo
 * description: Spu业务对象。由Service层输出的封装业务逻辑的对象。
 * author: gerry
 * created: 2019-11-24 23:05
 **/
@Data
public class SpuBo extends Spu {
    /**
     * 商品分类名称
     */
    @Transient
    private String cname;
    /**
     * 品牌名称
     */
    @Transient
    private String bname;

    /**
     * 商品详情
     */
    @Transient
    private SpuDetail spuDetail;

    /**
     * sku列表
     */
    @Transient
    private List<Sku> skus;

    public SpuBo() {
    }

    public SpuBo(Long brandId, Long cid1, Long cid2, Long cid3, String title, String subTitle,
                 Boolean saleable, Boolean valid, Date createTime, Date lastUpdateTime) {
        super(brandId, cid1, cid2, cid3, title, subTitle, saleable, valid, createTime, lastUpdateTime);
    }

}
