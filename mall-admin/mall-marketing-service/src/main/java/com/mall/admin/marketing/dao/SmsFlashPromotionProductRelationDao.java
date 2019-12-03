package com.mall.admin.marketing.dao;

import com.mall.admin.marketing.vo.SmsFlashPromotionProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 限时购商品关联自定义Dao
 * Created by macro on 2018/11/16.
 */
public interface SmsFlashPromotionProductRelationDao {
    /**
     * 获取限时购及相关商品信息
     */
    List<SmsFlashPromotionProductVO> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
