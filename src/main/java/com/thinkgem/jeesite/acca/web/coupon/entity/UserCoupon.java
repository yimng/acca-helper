package com.thinkgem.jeesite.acca.web.coupon.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCoupon extends DataEntity<UserCoupon> {
    private Long userCouponId;
    private Long userId;
    private Long couponId;
}
