package com.thinkgem.jeesite.acca.web.coupon.dao;

import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 代金券Dao
 * @author Lukun
 * @version 2018.0829
 */
@MyBatisDao
public interface CouponDao extends CrudDao<Coupon> {
    public List<Coupon> findListByName(String name);
}
