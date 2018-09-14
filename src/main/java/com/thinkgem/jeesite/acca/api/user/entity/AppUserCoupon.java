package com.thinkgem.jeesite.acca.api.user.entity;

import com.thinkgem.jeesite.acca.web.coupon.entity.UserCoupon;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserCoupon extends DataEntity<AppUserCoupon> {
    private Long userId;
    private String couponId;
}
