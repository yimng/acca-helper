package com.thinkgem.jeesite.acca.web.coupon.dao;

import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tk.mybatis.mapper.common.Mapper;
@MyBatisDao
public interface CouponMapper extends Mapper<Coupon> {
}