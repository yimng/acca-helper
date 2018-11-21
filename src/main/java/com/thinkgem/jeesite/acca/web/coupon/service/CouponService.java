package com.thinkgem.jeesite.acca.web.coupon.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.api.order.entity.AppOrder;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaUserDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.content.dao.ActivityMapper;
import com.thinkgem.jeesite.acca.web.content.entity.Activity;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.coupon.dao.CouponMapper;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.exam.dao.WebOrderDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import com.thinkgem.jeesite.acca.web.user.service.InviteService;
import com.thinkgem.jeesite.acca.web.user.service.UserCouponService;
import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.service.MyService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.sms.SmsUtils;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class CouponService extends MyService<CouponMapper, Coupon> {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserDao userDao;

    @Autowired
    private AppAccaUserDao appAccaUserDao;

    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private InviteService inviteService;

    @Autowired
    private WebOrderDao orderDao;

    @Autowired
    private WebMsgSysService msgSysService;


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

    private List<Coupon> findActiveCoupon() {
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

    public void publishCoupon(AppOrder order ) {
        // 把订单和优惠券关联起来，等订单审核通过后发放优惠券, 这时的优惠券状态为NEW的状态
        List<Coupon> activeList = findActiveCoupon();
        for (Coupon coupon : activeList) {
            if (!coupon.getFlag2()) {
                continue;
            }
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setCouponId(coupon.getId());
            userCoupon.setUserId(order.getAccaUserId());
            userCoupon.setCouponStatus(Constants.CouponStatus.NEW.getStatus());
            userCoupon.setOrderId(order.getOrderId());
            userCoupon.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
            userCouponService.saveOrUpdate(userCoupon);
        }
    }

    public void confirmCoupon(WebOrder order) {
        WebOrder webOrder = orderDao.get(order);
        Long accaUserId = webOrder.getAccaUserId();
        if (webOrder.getOrderStatus() == Constants.OrderStatus.checkSuccess) {
            // 订单审核通过，确定发放优惠券给用户 并且如果使用了优惠券，更新优惠券的状态为使用
            List<UserCoupon> userCoupons = userCouponService.getUserCouponByOrder(order);
            for (UserCoupon userCoupon : userCoupons) {
                if(Constants.CouponStatus.NEW.getStatus().equals(userCoupon.getCouponStatus())) {
                    userCoupon.setCouponStatus(Constants.CouponStatus.CONFIRM.getStatus());
                    userCouponService.saveOrUpdate(userCoupon);

                    Coupon coupon = dao.selectByPrimaryKey(userCoupon.getCouponId());
                    String phone = webOrder.getPhone();
                    sendMessages(accaUserId, coupon, phone);
                    coupon.setReceived(coupon.getReceived() + 1);
                    dao.updateByPrimaryKeySelective(coupon);
                }
                if (Constants.CouponStatus.USING.getStatus().equals(userCoupon.getCouponStatus())) {
                    userCoupon.setCouponStatus(Constants.CouponStatus.USED.getStatus());
                    userCouponService.saveOrUpdate(userCoupon);
                    Coupon coupon = dao.selectByPrimaryKey(userCoupon.getCouponId());
                    coupon.setConsumed(coupon.getConsumed() + 1);
                    dao.updateByPrimaryKeySelective(coupon);
                }
            }

        } else if (webOrder.getOrderStatus() == Constants.OrderStatus.checkSupplement
                    || webOrder.getOrderStatus() == Constants.OrderStatus.checkFail
                    || webOrder.getOrderStatus() == Constants.OrderStatus.checkSupplement) {
            List<UserCoupon> userCoupons = userCouponService.getUserCouponByOrder(order);
            for (UserCoupon userCoupon : userCoupons) {
                if (Constants.CouponStatus.USING.getStatus().equals(userCoupon.getCouponStatus())) {
                    //把优惠券返还给用户
                    userCoupon.setCouponStatus(Constants.CouponStatus.CONFIRM.getStatus());
                    userCouponService.saveOrUpdate(userCoupon);
                }
            }
        }

    }

    private void sendMessages(Long accaUserId, Coupon coupon, String phone) {
        msgSysService.savePushToPersonal("收到一个优惠券", "您已收到一个HELPER通用红包，金额" + coupon.getPrice() + "元，有效期" + coupon.getValidityStart() + " - " + coupon.getValidityEnd() + "，F1-F4机考在线预约可用！", accaUserId);
        SmsUtils.sendSms2Vcode(phone, "您已收到一个HELPER通用红包，金额\" +coupon.getPrice() + \"元，有效期\"+coupon.getValidityStart()+\" - \"+coupon.getValidityEnd()+\"，F1-F4机考在线预约可用！");
    }

    public void publishCouponForRegister(String phone) {
        //如果做活动期间被邀请，给用户分配代金券
        List<Coupon> activeList = findActiveCoupon();
        for (Coupon coupon : activeList) {
            if (coupon.getFlag2()) {
                continue;
            }
            //新注册用户
            if (coupon.getFlag1()) {
                UserCoupon userCoupon = new UserCoupon();
                Long accaUserId = appAccaUserDao.getAccaUserByPhone(phone).getAccaUserId();
                userCoupon.setUserId(accaUserId);
                userCoupon.setCouponId(coupon.getId());
                userCoupon.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
                userCoupon.setCouponStatus(Constants.CouponStatus.CONFIRM.getStatus());
                userCouponService.saveOrUpdate(userCoupon);
                sendMessages(accaUserId, coupon, phone);
                coupon.setReceived(coupon.getReceived() + 1);
                dao.updateByPrimaryKeySelective(coupon);

            } else if (coupon.getFlag3() || coupon.getFlag4()) { //邀请用户和被邀请用户
                List<Invite> invites = inviteService.getAppInvitesByPhoneAndInviteTime(phone,
                        coupon.getActivityStart(), coupon.getActivityEnd());
                for (Invite invite : invites) {
                    // invite failed
                    if (invite.getInviteStatus() == 2) {
                        continue;
                    }
                    invite.setSuccessTime(new Date());
                    inviteService.updateByPrimaryKeySelective(invite);
                    if (coupon.getFlag3()) {
                        AppAccaUser inviter = appAccaUserDao.getAccaUserByPhone(invite.getInviterPhone());
                        UserCoupon inviterCoupon = new UserCoupon();
                        inviterCoupon.setCouponId(coupon.getId());
                        inviterCoupon.setUserId(inviter.getAccaUserId());
                        inviterCoupon.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
                        inviterCoupon.setCouponStatus(Constants.CouponStatus.CONFIRM.getStatus());
                        userCouponService.saveOrUpdate(inviterCoupon);
                        sendMessages(inviter.getAccaUserId(), coupon, inviter.getPhone());
                        coupon.setReceived(coupon.getReceived() + 1);
                        dao.updateByPrimaryKeySelective(coupon);

                    }
                    if (coupon.getFlag4()) {
                        AppAccaUser invitee = appAccaUserDao.getAccaUserByPhone(invite.getInviteePhone());
                        UserCoupon inviteeCoupon = new UserCoupon();
                        inviteeCoupon.setCouponId(coupon.getId());
                        inviteeCoupon.setUserId(invitee.getAccaUserId());
                        inviteeCoupon.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
                        inviteeCoupon.setCouponStatus(Constants.CouponStatus.CONFIRM.getStatus());
                        userCouponService.saveOrUpdate(inviteeCoupon);
                        sendMessages(invitee.getAccaUserId(), coupon, invitee.getPhone());
                        coupon.setReceived(coupon.getReceived() + 1);
                        dao.updateByPrimaryKeySelective(coupon);

                    }
                }
            }

        }
    }

}
