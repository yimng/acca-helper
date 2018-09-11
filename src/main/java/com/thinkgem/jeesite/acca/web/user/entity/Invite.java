package com.thinkgem.jeesite.acca.web.user.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.omg.CORBA.UNKNOWN;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = true)
public class Invite extends DataEntity<Invite> {
    private static final long serialVersionUID = 1L;

    private String inviterPhone;
    private String inviteePhone;
    private Date inviteTime;
    private Date inviteStart;
    private Date inviteEnd;
    private Date successTime;
    private Integer inviteStatus;
    private String inviteStatusName;

    private static final int INVITE_TIME_GAP = 3;


    public int getInviteStatus () {
        if (inviteStatus != null) {
            return inviteStatus;
        }
        if (successTime != null) {
            this.inviteStatus = InviteStatus.SUCESS.getId();
            return this.inviteStatus;
        }
        if (inviteTime == null) {
            this.inviteStatus = InviteStatus.UNKOWN.getId();
            return this.inviteStatus;
        }
        if (DateUtils.getDistanceOfTwoDate(inviteTime, new Date()) <= INVITE_TIME_GAP) {
            this.inviteStatus = InviteStatus.INVITING.getId();
            return this.inviteStatus;
        } else {
            this.inviteStatus = InviteStatus.FAILED.getId();
            return this.inviteStatus;
        }
    }

    public String getInviteStatusName(){
        int status = getInviteStatus();
        switch(status){
            case -1:
                return InviteStatus.UNKOWN.getName();
            case 0:
                return InviteStatus.INVITING.getName();
            case 1:
                return InviteStatus.SUCESS.getName();
            case 2:
                return InviteStatus.FAILED.getName();
            default:
                return InviteStatus.UNKOWN.getName();
        }
    }

    public enum InviteStatus {
        UNKOWN("请选择", -1),
        //受邀中
        INVITING("受邀中", 0),
        //邀请成功
        SUCESS("邀请成功", 1),
        //邀请失败
        FAILED("邀请失败", 2);

        private  String name;
        private  int id;

        private InviteStatus(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}
