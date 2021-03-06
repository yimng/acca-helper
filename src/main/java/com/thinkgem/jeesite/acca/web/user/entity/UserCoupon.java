package com.thinkgem.jeesite.acca.web.user.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_user_coupon")
public class UserCoupon implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "del_flag")
    private String delFlag;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exam_place_id")
    private Long examPlaceId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "coupon_status")
    private String couponStatus;

    @Column(name = "select_time")
    private Date selectTime;

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

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return coupon_id
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * @param couponId
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * @return coupon_status
     */
    public String getCouponStatus() {
        return couponStatus;
    }

    /**
     * @param couponStatus
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    /**
     * @return select_time
     */
    public Date getSelectTime() {
        return selectTime;
    }

    /**
     * @param selectTime
     */
    public void setSelectTime(Date selectTime) {
        this.selectTime = selectTime;
    }
}