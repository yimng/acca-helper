package com.thinkgem.jeesite.acca.web.coupon.service;

import com.thinkgem.jeesite.acca.web.coupon.dao.UserCouponDao;
import com.thinkgem.jeesite.acca.web.coupon.entity.UserCoupon;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class UserCouponService extends CrudService<UserCouponDao, UserCoupon> {
    @Override
    public UserCoupon get(String id) {
        return super.get(id);
    }

    @Override
    public List<UserCoupon> findList(UserCoupon userCoupon) {
        return super.findList(userCoupon);
    }

    @Override
    public Page<UserCoupon> findPage(Page<UserCoupon> page, UserCoupon userCoupon) {
        return super.findPage(page, userCoupon);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(UserCoupon userCoupon) {
        super.save(userCoupon);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(UserCoupon userCoupon) {
        super.delete(userCoupon);
    }
}
