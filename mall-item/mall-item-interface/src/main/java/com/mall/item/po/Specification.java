package com.mall.item.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Table(name = "tb_specification")
@Data
public class Specification implements Serializable {
    @Id
    private Long categoryId;
    private String specifications;
}
