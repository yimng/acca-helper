package com.thinkgem.jeesite.acca.web.feedback.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_exam_center_question")
public class ExamCenterQuestion implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 问题名称
     */
    private String titile;

    @Column(name = "exam_place_id")
    private Long examPlaceId;

    /**
     * 机考中心名称
     */
    @Column(name = "exam_place_name")
    private String examPlaceName;

    @Column(name = "acca_user_id")
    private Long accaUserId;

    /**
     * 提问者名称
     */
    @Column(name = "acca_user_nickname")
    private String accaUserNickname;

    /**
     * 提问者手机号
     */
    @Column(name = "acca_user_phone")
    private String accaUserPhone;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取问题名称
     *
     * @return titile - 问题名称
     */
    public String getTitile() {
        return titile;
    }

    /**
     * 设置问题名称
     *
     * @param titile 问题名称
     */
    public void setTitile(String titile) {
        this.titile = titile;
    }

    /**
     * @return exam_place_id
     */
    public Long getExamPlaceId() {
        return examPlaceId;
    }

    /**
     * @param examPlaceId
     */
    public void setExamPlaceId(Long examPlaceId) {
        this.examPlaceId = examPlaceId;
    }

    /**
     * 获取机考中心名称
     *
     * @return exam_place_name - 机考中心名称
     */
    public String getExamPlaceName() {
        return examPlaceName;
    }

    /**
     * 设置机考中心名称
     *
     * @param examPlaceName 机考中心名称
     */
    public void setExamPlaceName(String examPlaceName) {
        this.examPlaceName = examPlaceName;
    }

    /**
     * @return acca_user_id
     */
    public Long getAccaUserId() {
        return accaUserId;
    }

    /**
     * @param accaUserId
     */
    public void setAccaUserId(Long accaUserId) {
        this.accaUserId = accaUserId;
    }

    /**
     * 获取提问者名称
     *
     * @return acca_user_nickname - 提问者名称
     */
    public String getAccaUserNickname() {
        return accaUserNickname;
    }

    /**
     * 设置提问者名称
     *
     * @param accaUserNickname 提问者名称
     */
    public void setAccaUserNickname(String accaUserNickname) {
        this.accaUserNickname = accaUserNickname;
    }

    /**
     * 获取提问者手机号
     *
     * @return acca_user_phone - 提问者手机号
     */
    public String getAccaUserPhone() {
        return accaUserPhone;
    }

    /**
     * 设置提问者手机号
     *
     * @param accaUserPhone 提问者手机号
     */
    public void setAccaUserPhone(String accaUserPhone) {
        this.accaUserPhone = accaUserPhone;
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