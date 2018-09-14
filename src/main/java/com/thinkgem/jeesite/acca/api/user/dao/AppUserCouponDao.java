package com.thinkgem.jeesite.acca.api.user.dao;

import com.thinkgem.jeesite.acca.api.user.entity.AppUserCoupon;
import com.thinkgem.jeesite.acca.web.coupon.entity.UserCoupon;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface AppUserCouponDao extends CrudDao<AppUserCoupon> {
}
