package com.thinkgem.jeesite.acca.web.coupon.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.web.content.dao.ActivityMapper;
import com.thinkgem.jeesite.acca.web.content.entity.Activity;
import com.thinkgem.jeesite.acca.web.coupon.dao.CouponMapper;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.service.MyService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.beans.Transient;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class CouponService extends MyService<CouponMapper, Coupon> {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserDao userDao;

    public Coupon get(Long id) {
        Coupon coupon = dao.selectByPrimaryKey(id);
        Activity activity = activityMapper.selectByPrimaryKey(coupon.getActivityId());
        coupon.setActivityStart(activity.getBeginTime());
        coupon.setActivityEnd(activity.getEndTime());
        coupon.setActivityName(activity.getActivityName());
        return coupon;
    }

    public int getAvailableCouponsByCouponId(Long id) {
        Coupon coupon = dao.selectByPrimaryKey(id);
        return coupon.getNumber() - coupon.getReceived();
    }


    @Override
    public List<Coupon> findList(Coupon coupon) {
        return dao.select(coupon);
    }

    @Override
    public PageInfo<Coupon> findPage(Coupon coupon, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Coupon> coupons = dao.findList(coupon);

        return new PageInfo<>(coupons);
    }

    @Transactional(readOnly = false)
    public void update(Coupon coupon) {
        Example example = new Example(Coupon.class);
        Example.Criteria criteria1 = example.createCriteria().andEqualTo("id", coupon.getId());
        dao.updateByExampleSelective(coupon, example);
    }

    @Override
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
            coupon.setConsumed(0);
            coupon.setReceived(0);
            User user = UserUtils.getUser();
            if (StringUtils.isNotEmpty(user.getId())) {
                coupon.setCreateBy(user.getId());
            }
            dao.insert(coupon);
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
            dao.updateByExampleSelective(coupon, example1);
        }

    }

    @Override
    public void delete(Coupon coupon) {
        dao.delete(coupon);
    }

    public List<Coupon> findActiveCoupon() {
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThan("beginTime", new Date());
        criteria.andGreaterThan("endTime", new Date());
        List<Activity> activities = activityMapper.selectByExample(example);
        if (activities.size() == 0) {
            return new ArrayList<>();
        }
        Map<Long, Activity> actMap = new HashMap<>();
        for (Activity act : activities) {
            actMap.put(act.getActivityId(), act);
        }

        Example example1 = new Example(Coupon.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andIn("activityId", actMap.keySet());
        List<Coupon> coupons = dao.selectByExample(example1);
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
