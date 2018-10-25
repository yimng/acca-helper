package com.thinkgem.jeesite.acca.web.feedback.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_exam_center_answer")
public class ExamCenterAnswer implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "sys_user_id")
    private String sysUserId;

    @Column(name = "sys_user_name")
    private String sysUserName;

    @Column(name = "sys_user_phone")
    private String sysUserPhone;

    @Column(name = "create_time")
    private Date createTime;

    private String content;

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
     * @return question_id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * @return sys_user_id
     */
    public String getSysUserId() {
        return sysUserId;
    }

    /**
     * @param sysUserId
     */
    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * @return sys_user_name
     */
    public String getSysUserName() {
        return sysUserName;
    }

    /**
     * @param sysUserName
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    /**
     * @return sys_user_phone
     */
    public String getSysUserPhone() {
        return sysUserPhone;
    }

    /**
     * @param sysUserPhone
     */
    public void setSysUserPhone(String sysUserPhone) {
        this.sysUserPhone = sysUserPhone;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
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