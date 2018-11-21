package com.thinkgem.jeesite.acca.web.user.service;

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

    @Transactional(readOnly = false)
    public void saveOrUpdate(UserCoupon userCoupon) {
        if (userCoupon.getId() != null) {
            userCouponMapper.updateByPrimaryKeySelective(userCoupon);
        } else {
            userCouponMapper.insert(userCoupon);
        }
    }

    @Transactional(readOnly = false)
    public int savebatch(List<UserCoupon> userCouponList) {
        return userCouponMapper.insertList(userCouponList);
    }

    public List<SmallCoupon> getCouponListByUserId(Long id) {
        List<SmallCoupon> couponsByUserId = userCouponMapper.getCouponsByUserId(id);
        return couponsByUserId;
    }

    public List<UserCoupon> getUserCouponByOrder(WebOrder order) {
        Example example = new Example(UserCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", order.getAccaUserId());
        criteria.andEqualTo("orderId", order.getOrderId());
        criteria.andEqualTo("delFlag", BaseEntity.DEL_FLAG_NORMAL);
        return userCouponMapper.selectByExample(example);
    }
}
