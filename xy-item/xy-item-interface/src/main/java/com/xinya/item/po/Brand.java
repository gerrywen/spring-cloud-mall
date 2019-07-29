package com.xinya.item.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_brand")
@Data
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue注释是配置指定列(字段)增量的方式
    @Column(name = "id")
    private Long id;
    /**
     * 品牌名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 品牌图片
     */
    private String image;
    /**
     * 品牌的首字母
     */
    private Character letter;
}
