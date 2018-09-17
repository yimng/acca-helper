package com.thinkgem.jeesite.acca.web.user.service;

import com.thinkgem.jeesite.acca.web.user.dao.UserCouponMapper;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class UserCouponService {
    @Autowired
    private UserCouponMapper userCouponMapper;

    public void saveOrUpdate(UserCoupon userCoupon) {
        if (userCoupon.getId() != null) {
            Example example = new Example(UserCoupon.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", userCoupon.getId());
            userCouponMapper.updateByExampleSelective(userCoupon, example);
        } else {
            userCouponMapper.insert(userCoupon);
        }
    }
}
