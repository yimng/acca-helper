package com.thinkgem.jeesite.acca.web.user.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = true)
public class Invite extends DataEntity<Invite> {
    private static final long serialVersionUID = 1L;

    private String invitorPhone;
    private String inviteePhone;
    private Date inviteTime;
    private Date successTime;

    private InviteStatus inviteStatus;
    private int INVITE_TIME_GAP = 3;


    private InviteStatus getInviteStatus () {
        if (successTime != null) {
            return InviteStatus.SUCESS;
        }
        if (DateUtils.getDistanceOfTwoDate(inviteTime, new Date()) <= INVITE_TIME_GAP) {
            return InviteStatus.INVITING;
        } else {
            return InviteStatus.FAILED;
        }
    }

    enum InviteStatus {
        INVITING,
        SUCESS,
        FAILED
    }
}
