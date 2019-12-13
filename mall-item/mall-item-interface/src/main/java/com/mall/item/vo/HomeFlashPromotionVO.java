package com.mall.item.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 首页当前秒杀场次信息
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
public class HomeFlashPromotionVO {
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 下场开始时间
     */
    private Date nextStartTime;
    /**
     * 下场结束时间
     */
    private Date nextEndTime;
    /**
     * 属于该秒杀活动的商品
     */
    private List<FlashPromotionProductVO> productList;
}
