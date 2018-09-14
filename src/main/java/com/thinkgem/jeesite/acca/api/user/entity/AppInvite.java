package com.thinkgem.jeesite.acca.api.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbl_invite")
public class AppInvite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select replace(UUID(),-,')")
    private String id;

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
     * 邀请时间
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
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
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
     * 获取邀请时间
     *
     * @return invite_time - 邀请时间
     */
    public Date getInviteTime() {
        return inviteTime;
    }

    /**
     * 设置邀请时间
     *
     * @param inviteTime 邀请时间
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
}