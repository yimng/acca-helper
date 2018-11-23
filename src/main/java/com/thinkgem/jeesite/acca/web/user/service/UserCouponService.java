package com.thinkgem.jeesite.acca.web.user.service;

import com.thinkgem.jeesite.acca.web.coupon.dao.CouponMapper;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.acca.web.user.dao.UserCouponMapper;
import com.thinkgem.jeesite.acca.web.user.entity.SmallCoupon;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import com.thinkgem.jeesite.common.persistence.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserCouponService {
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private CouponMapper couponMapper;

    @Transactional(readOnly = false)
    public void saveOrUpdate(UserCoupon userCoupon) {
        if (userCoupon.getId() != null) {
            userCouponMapper.updateByPrimaryKeySelective(userCoupon);
        } else {
            userCouponMapper.insert(userCoupon);
        }
    }

    public List<SmallCoupon> getCouponListByUserId(Long id) {
        List<SmallCoupon> couponsByUserId = userCouponMapper.getCouponsByUserId(id);
        return couponsByUserId;
    }

    public List<UserCoupon> getUserCouponByOrder(Long orderId) {
        Example example = new Example(UserCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        criteria.andEqualTo("delFlag", BaseEntity.DEL_FLAG_NORMAL);
        return userCouponMapper.selectByExample(example);
    }

    @Transactional(readOnly = false)
    public void deleteUserCouponByOrder(Long orderId) {
        Example example = new Example(UserCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
        userCouponMapper.updateByExampleSelective(userCoupon, example);
    }

    public Float getCouponPriceByUserCouponId(Long userCouponId) {
        UserCoupon userCoupon = userCouponMapper.selectByPrimaryKey(userCouponId);
        if (userCoupon == null) {
            return 0.0f;
        }

        Coupon coupon = couponMapper.selectByPrimaryKey(userCoupon.getCouponId());
        if (coupon == null) {
            return 0.0f;
        }
        return coupon.getPrice();
    }

}
