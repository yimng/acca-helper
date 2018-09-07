package com.thinkgem.jeesite.acca.api.user.service;

import com.thinkgem.jeesite.acca.api.model.request.InviteReq;
import com.thinkgem.jeesite.acca.api.user.dao.AppInviteDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppInvite;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class AppInviteService extends CrudService<AppInviteDao, AppInvite> {


    @Transactional(readOnly = false)
    public BaseResponse invite(InviteReq invitereq) {
        if (!AppUtils.isMobileNum(invitereq.getInviterPhone())) {
            return new BaseResponse(RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR);
        }
        if (!AppUtils.isMobileNum(invitereq.getInviteePhone())) {
            return new BaseResponse(RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR);
        }
        AppInvite appInvite = new AppInvite();
        appInvite.setInviterPhone(invitereq.getInviterPhone());
        appInvite.setInviteePhone(invitereq.getInviteePhone());
        appInvite.setInviteTime(new Date());
        super.save(appInvite);
        BaseResponse resp = new BaseResponse(RespConstants.GLOBAL_SUCCESS);
        return resp;
    }
}
