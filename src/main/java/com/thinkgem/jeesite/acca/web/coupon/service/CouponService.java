package com.thinkgem.jeesite.acca.web.coupon.service;

import com.thinkgem.jeesite.acca.web.content.dao.ActivityMapper;
import com.thinkgem.jeesite.acca.web.content.entity.Activity;
import com.thinkgem.jeesite.acca.web.coupon.dao.CouponMapper;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.user.dao.WebAccaUserDao;
import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserDao userDao;

    public Coupon get(Long id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        Activity activity = activityMapper.selectByPrimaryKey(coupon.getActivityId());
        coupon.setActivityStart(activity.getBeginTime());
        coupon.setActivityEnd(activity.getEndTime());
        coupon.setActivityName(activity.getActivityName());
        return coupon;
    }

    public List<Coupon> findList(Coupon coupon) {
        return couponMapper.select(coupon);
    }

    public Page<Coupon> findPage(Page<Coupon> page, Coupon coupon) {
        coupon.setPage(page);
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(coupon.getActivityName())) {
            criteria.andLike("activityName", "%" + coupon.getActivityName() + "%");
        }
        criteria.andGreaterThan("beginTime", coupon.getActivityStart());
        criteria.andLessThan("endTime", coupon.getActivityEnd());
        List<Activity> activities = activityMapper.selectByExample(example);
        if (activities.size() == 0) {
            return page;
        }
        Map<Long, Activity> actMap = new HashMap<>();
        for (Activity act : activities) {
            actMap.put(act.getActivityId(), act);
        }

        Example example1 = new Example(Coupon.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andIn("activityId", actMap.keySet());
        List<Coupon> coupons = couponMapper.selectByExample(example1);
        for (Coupon c : coupons) {
            Activity activity = actMap.get(c.getActivityId());
            c.setActivityName(activity.getActivityName());
            c.setActivityStart(activity.getBeginTime());
            c.setActivityEnd(activity.getEndTime());
            User user = userDao.get(c.getCreateBy());
            c.setCreator(user.getName());
        }

        page.setList(coupons);
        return page;
    }

    @Transactional(readOnly = false)
    public void save(Coupon coupon) {
        if (coupon.getId() == null) {
            Activity activity = new Activity();
            activity.setActivityName(coupon.getActivityName());
            activity.setBeginTime(coupon.getActivityStart());
            activity.setEndTime(coupon.getActivityEnd());
            activityMapper.insert(activity);
            coupon.setDelFlag("0");
            coupon.setCreateDate(new Date());
            coupon.setUpdateDate(new Date());
            coupon.setActivityId(activity.getActivityId());
            User user = UserUtils.getUser();
            if (StringUtils.isNotEmpty(user.getId())) {
                coupon.setCreateBy(user.getId());
            }
            couponMapper.insert(coupon);
        } else {
            Activity activity = activityMapper.selectByPrimaryKey(coupon.getActivityId());
            activity.setActivityName(coupon.getActivityName());
            activity.setBeginTime(coupon.getActivityStart());
            activity.setEndTime(coupon.getActivityEnd());

            Example example = new Example(Activity.class);
            Example.Criteria criteria = example.createCriteria().andEqualTo("activityId", activity.getActivityId());
            activityMapper.updateByExampleSelective(activity, example);

            Example example1 = new Example(Coupon.class);
            Example.Criteria criteria1 = example1.createCriteria().andEqualTo("id", coupon.getId());
            couponMapper.updateByExampleSelective(coupon, example1);
        }

    }

    @Transactional(readOnly = false)
    public void delete(Coupon coupon) {
        couponMapper.delete(coupon);
    }

    public List<Coupon> findActiveCoupon() {
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThan("beginTime", new Date());
        criteria.andGreaterThan("endTime", new Date());
        List<Activity> activities = activityMapper.selectByExample(example);
        Map<Long, Activity> actMap = new HashMap<>();
        for (Activity act : activities) {
            actMap.put(act.getActivityId(), act);
        }

        Example example1 = new Example(Coupon.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andIn("activityId", actMap.keySet());
        List<Coupon> coupons = couponMapper.selectByExample(example1);
        for (Coupon c : coupons) {
            Activity activity = actMap.get(c.getActivityId());
            c.setActivityName(activity.getActivityName());
            c.setActivityStart(activity.getBeginTime());
            c.setActivityEnd(activity.getEndTime());
            User user = userDao.get(c.getCreateBy());
            c.setCreator(user.getName());
        }

        return coupons;
    }
}
