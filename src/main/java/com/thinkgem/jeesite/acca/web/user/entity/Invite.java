package com.thinkgem.jeesite.acca.web.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "tbl_invite")
public class Invite implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 邀请人手机号
     */
    @Column(name = "inviter_phone")
    private String inviterPhone;

    /**
     * 被邀请从手机号
     */
    @Column(name = "invitee_phone")
    private String inviteePhone;

    /**
     * 邀请发出时间
     */
    @Column(name = "invite_time")
    private Date inviteTime;

    /**
     * 邀请成功时间
     */
    @Column(name = "success_time")
    private Date successTime;

    @Column(name = "del_flag")
    private String delFlag;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取邀请人手机号
     *
     * @return inviter_phone - 邀请人手机号
     */
    public String getInviterPhone() {
        return inviterPhone;
    }

    /**
     * 设置邀请人手机号
     *
     * @param inviterPhone 邀请人手机号
     */
    public void setInviterPhone(String inviterPhone) {
        this.inviterPhone = inviterPhone;
    }

    /**
     * 获取被邀请从手机号
     *
     * @return invitee_phone - 被邀请从手机号
     */
    public String getInviteePhone() {
        return inviteePhone;
    }

    /**
     * 设置被邀请从手机号
     *
     * @param inviteePhone 被邀请从手机号
     */
    public void setInviteePhone(String inviteePhone) {
        this.inviteePhone = inviteePhone;
    }

    /**
     * 获取邀请发出时间
     *
     * @return invite_time - 邀请发出时间
     */
    public Date getInviteTime() {
        return inviteTime;
    }

    /**
     * 设置邀请发出时间
     *
     * @param inviteTime 邀请发出时间
     */
    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
    }

    /**
     * 获取邀请成功时间
     *
     * @return success_time - 邀请成功时间
     */
    public Date getSuccessTime() {
        return successTime;
    }

    /**
     * 设置邀请成功时间
     *
     * @param successTime 邀请成功时间
     */
    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    /**
     * @return del_flag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getInviteStatus(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -3);
        Date start = cal.getTime();
        // success
        if (successTime != null) {
            return 1;
            // inviting
        } else if (inviteTime != null && inviteTime.after(start) && inviteTime.before(new Date())){
            return 0;
            //failed
        } else if (inviteTime != null && inviteTime.before(start)) {
            return 2;
        } else {
            return -1;
        }


    }
    @Transient
    private Date inviteStart;
    @Transient
    private Date inviteEnd;
}