package com.thinkgem.jeesite.acca.api.user.service;

import com.thinkgem.jeesite.acca.api.model.request.InviteReq;
import com.thinkgem.jeesite.acca.api.user.dao.AppInviteDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppInviteMapper;
import com.thinkgem.jeesite.acca.api.user.entity.AppInvite;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AppInviteService {

    @Autowired
    private AppInviteMapper inviteMapper;

    @Transactional(readOnly = false)
    public BaseResponse invite(InviteReq invitereq) {

        AppInvite appInvite = new AppInvite();
        appInvite.setInviterPhone(invitereq.getInviterPhone());
        appInvite.setInviteePhone(invitereq.getInviteePhone());
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

        List<AppInvite> appInvites = getAppInvitesByPhoneAndInviteTime(phone, start, end);
        if (appInvites.size() > 0) {
            return true;
        }
        return false;
    }

    public List<AppInvite> getAppInvitesByPhoneAndInviteTime(String phone, Date start, Date end) {
        Example example = new Example(AppInvite.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inviterPhone", phone);
        criteria.andEqualTo("delFlag", 0);
        criteria.andBetween("inviteTime", start, end);
        return inviteMapper.selectByExample(example);
    }

    public int updateByPrimaryKeySelective(AppInvite appInvite) {
        return inviteMapper.updateByPrimaryKeySelective(appInvite);
    }

}
