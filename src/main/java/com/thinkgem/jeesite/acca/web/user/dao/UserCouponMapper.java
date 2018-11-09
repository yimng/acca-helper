package com.thinkgem.jeesite.acca.web.user.dao;

import com.thinkgem.jeesite.acca.web.user.entity.SmallCoupon;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import com.thinkgem.jeesite.common.persistence.MyMapper;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

@MyBatisDao
public interface UserCouponMapper extends MyMapper<UserCoupon> {

    List<SmallCoupon> getCouponsByUserId(Long accaUserId);
}