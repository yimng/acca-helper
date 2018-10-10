package com.thinkgem.jeesite.acca.web.coupon.dao;

import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;

import com.thinkgem.jeesite.common.persistence.MyMapper;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@MyBatisDao
public interface CouponMapper extends MyMapper<Coupon> {
    List<Coupon> findList(Coupon coupon);
}