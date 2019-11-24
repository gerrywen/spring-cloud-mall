package com.mall.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_spu")
@Data
public class Spu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long brandId;
    /**
     * 1级类目
     */
    private Long cid1;
    /**
     * 2级类目
     */
    private Long cid2;
    /**
     * 3级类目
     */
    private Long cid3;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 是否上架
     */
    private Boolean saleable;
    /**
     * 是否有效，逻辑删除使用
     */
    private Boolean valid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    public Spu() {
    }

    public Spu(Long brandId, Long cid1, Long cid2, Long cid3, String title, String subTitle,
               Boolean saleable, Boolean valid, Date createTime, Date lastUpdateTime) {
        this.brandId = brandId;
        this.cid1 = cid1;
        this.cid2 = cid2;
        this.cid3 = cid3;
        this.title = title;
        this.subTitle = subTitle;
        this.saleable = saleable;
        this.valid = valid;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

}
