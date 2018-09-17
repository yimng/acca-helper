package com.thinkgem.jeesite.acca.web.user.service;

import com.thinkgem.jeesite.acca.api.model.request.InviteReq;
import com.thinkgem.jeesite.acca.web.user.dao.InviteMapper;
import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.common.persistence.Page;
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
    public Page<Invite> findPage(Page<Invite> page, Invite invite) {
        Example example = new Example(Invite.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(invite.getInviterPhone())) {
            criteria.andLessThan("inviterPhone", "%"+invite.getInviterPhone() +"%");
        }
        if (invite.getInviteStart() != null) {
            criteria.andGreaterThan("inviteTime", invite.getInviteStart());
        }
        if (invite.getInviteEnd() != null) {
            criteria.andLessThan("inviteTime", invite.getInviteEnd());
        }
        if (invite.getInviteStatus() != null) {
            if (invite.getInviteStatus() == 0) {
                criteria.andLessThan("inviteTime", new Date());
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -3);
                Date start = cal.getTime();
                criteria.andGreaterThan("inviteTime", start);
            }
            if (invite.getInviteStatus() == 1) {
                criteria.andIsNotNull("successTime");
            }
            if (invite.getInviteStatus() == 2) {
                criteria.andGreaterThan("inviteTime", new Date());
            }

        }
        List<Invite> invites = inviteMapper.selectByExample(example);
        page.setList(invites);
        return page;
    }
//
//    @Transactional(readOnly = false)
//    public void save(Invite invite) {
//        super.save(invite);
//    }
//
//    @Transactional(readOnly = false)
//    public void delete(Invite invite) {
//        super.delete(invite);
//    }
//
//
    public List<InviteRank> findInviteRank(Date start, Date end) {
        return inviteMapper.findInviteRank(start, end);
    }



    @Transactional(readOnly = false)
    public BaseResponse invite(InviteReq invitereq) {

        Invite appInvite = new Invite();
        appInvite.setInviterPhone(invitereq.getInviterPhone());
        appInvite.setInviteePhone(invitereq.getInviteePhone());
        appInvite.setCouponId(invitereq.getCouponId());
        appInvite.setInviteTime(new Date());
        inviteMapper.insert(appInvite);
        BaseResponse resp = new BaseResponse(RespConstants.GLOBAL_SUCCESS);
        return resp;
    }

    public boolean getInviteStatus(String phone) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -3);
        Date start = cal.getTime();
        Date end = new Date();

        List<Invite> appInvites = getAppInvitesByPhoneAndInviteTime(phone, start, end);
        if (appInvites.size() > 0) {
            return true;
        }
        return false;
    }

    public List<Invite> getAppInvitesByPhoneAndInviteTime(String phone, Date start, Date end) {
        Example example = new Example(Invite.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inviteePhone", phone);
        criteria.andEqualTo("delFlag", 0);
        criteria.andBetween("inviteTime", start, end);
        return inviteMapper.selectByExample(example);
    }

    public int updateByPrimaryKeySelective(Invite appInvite) {
        return inviteMapper.updateByPrimaryKeySelective(appInvite);
    }
}
