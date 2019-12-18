package com.mall.oms.mapper;

import com.mall.admin.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品信息自定义Dao
 * Created by macro on 2018/9/3.
 */
public interface OmsAppOrderItemMapper {
    int insertList(@Param("list") List<OmsOrderItem> list);
}
