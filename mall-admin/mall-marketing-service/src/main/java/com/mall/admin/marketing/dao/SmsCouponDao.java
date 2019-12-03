package com.mall.admin.marketing.dao;

import com.mall.admin.marketing.vo.SmsCouponVO;
import org.apache.ibatis.annotations.Param;

/**
 * 优惠券管理自定义查询Dao
 * Created by macro on 2018/8/29.
 */
public interface SmsCouponDao {
    SmsCouponVO getItem(@Param("id") Long id);
}
