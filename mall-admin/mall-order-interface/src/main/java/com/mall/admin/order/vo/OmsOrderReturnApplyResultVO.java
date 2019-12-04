package com.mall.admin.order.vo;

import com.mall.admin.model.OmsCompanyAddress;
import com.mall.admin.model.OmsOrderReturnApply;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 * Created by macro on 2018/10/18.
 */
public class OmsOrderReturnApplyResultVO extends OmsOrderReturnApply {
    /**
     * 公司地址
     */
    @Getter
    @Setter
    private OmsCompanyAddress companyAddress;
}
