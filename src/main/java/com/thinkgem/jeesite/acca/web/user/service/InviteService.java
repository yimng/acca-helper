package com.thinkgem.jeesite.acca.web.user.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.api.model.request.InviteReq;
import com.thinkgem.jeesite.acca.web.user.dao.InviteMapper;
import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class InviteService {

    @Autowired
    private InviteMapper inviteMapper;

    public Invite get(Long id) {
        return inviteMapper.selectByPrimaryKey(id);
    }

    //
//    public List<Invite> findList(Invite invite) {
//        return super.findList(invite);
//    }
//
    public PageInfo<Invite> findPage(Invite invite, String status, int pageNo, int pageSize) {
        Example example = new Example(Invite.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("1=1");
        if (StringUtils.isNotBlank(invite.getInviterPhone())) {
            criteria.andLessThan("inviterPhone", "%" + invite.getInviterPhone() + "%");
        }
        if (invite.getInviteStart() != null) {
            criteria.andGreaterThan("inviteTime", invite.getInviteStart());
        }
        if (invite.getInviteEnd() != null) {
            criteria.andLessThan("inviteTime", invite.getInviteEnd());
        }
        if ("0".equals(status)) {
            criteria.andLessThan("inviteTime", new Date());
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -3);
            Date start = cal.getTime();
            criteria.andGreaterThan("inviteTime", start);
        }
        if ("1".equals(status)) {
            criteria.andIsNotNull("successTime");
        }
        if ("2".equals(status)) {
            criteria.andGreaterThan("inviteTime", new Date());
        }

        PageHelper.startPage(pageNo, pageSize);
        List<Invite> invites = inviteMapper.selectByExample(example);
        return new PageInfo<>(invites);
    }

    public List<InviteRank> findInviteRank(String start, String end, String sort, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return inviteMapper.findInviteRank(start, end, sort);
    }


    @Transactional(readOnly = false)
    public BaseResponse invite(InviteReq invitereq) {

        Invite invite = new Invite();
        invite.setInviterPhone(invitereq.getInviterPhone());
        invite.setInviteePhone(invitereq.getInviteePhone());
        invite.setCouponId(invitereq.getCouponId());
        invite.setInviteTime(new Date());
        invite.setDelFlag("0");
        inviteMapper.insert(invite);
        BaseResponse resp = new BaseResponse(RespConstants.GLOBAL_SUCCESS);
        return resp;
    }

    public boolean getInviteStatus(String phone) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -3);
        Date start = cal.getTime();
        Date end = new Date();

        List<Invite> appInvites = getAppInvitesByPhoneAndInviteTime(phone, start, end, null);
        if (appInvites.size() > 0) {
            return true;
        }
        return false;
    }

    public List<Invite> getAppInvitesByPhoneAndInviteTime(String phone, Date start, Date end, Long couponId) {
        Example example = new Example(Invite.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inviteePhone", phone);
        criteria.andEqualTo("delFlag", 0);
        criteria.andBetween("inviteTime", start, end);
        if (couponId != null) {
            criteria.andEqualTo("couponId", couponId);
        }
        return inviteMapper.selectByExample(example);
    }

    public int updateByPrimaryKeySelective(Invite appInvite) {
        return inviteMapper.updateByPrimaryKeySelective(appInvite);
    }

}
