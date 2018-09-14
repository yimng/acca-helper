package com.thinkgem.jeesite.acca.web.coupon.entity;

import com.thinkgem.jeesite.common.persistence.Page;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbl_coupon")
@Data
public class Coupon implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 活动名称
     */
    @Column(name = "activity_id")
    private Long activityId;

    /**
     * 代金券金额
     */
    private Float price;

    /**
     * 有效期开始时间
     */
    @Column(name = "validity_start")
    private Date validityStart;

    /**
     * 有效期结束时间
     */
    @Column(name = "validity_end")
    private Date validityEnd;

    /**
     * 代金券数量
     */
    private Integer number;

    /**
     * 代金券说明
     */
    private String description;

    /**
     * 发布者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 领取数量
     */
    private Integer received;

    /**
     * 使用数量
     */
    private Integer consumed;

    @Column(name = "del_flag")
    private String delFlag;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 是否指定用户
     */
    private Boolean assign;

    /**
     * 代金券类型 1.新注册用户 2.报考用户 3.邀请用户 4.被邀请用户，如果支持多个，采取位运算或
     */
    @Column(name = "coupon_type")
    private Integer couponType;

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
     * 获取活动名称
     *
     * @return activity_id - 活动名称
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 设置活动名称
     *
     * @param activityId 活动名称
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取代金券金额
     *
     * @return price - 代金券金额
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置代金券金额
     *
     * @param price 代金券金额
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取有效期开始时间
     *
     * @return validity_start - 有效期开始时间
     */
    public Date getValidityStart() {
        return validityStart;
    }

    /**
     * 设置有效期开始时间
     *
     * @param validityStart 有效期开始时间
     */
    public void setValidityStart(Date validityStart) {
        this.validityStart = validityStart;
    }

    /**
     * 获取有效期结束时间
     *
     * @return validity_end - 有效期结束时间
     */
    public Date getValidityEnd() {
        return validityEnd;
    }

    /**
     * 设置有效期结束时间
     *
     * @param validityEnd 有效期结束时间
     */
    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }

    /**
     * 获取代金券数量
     *
     * @return number - 代金券数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置代金券数量
     *
     * @param number 代金券数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取代金券说明
     *
     * @return description - 代金券说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置代金券说明
     *
     * @param description 代金券说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取发布者
     *
     * @return create_by - 发布者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置发布者
     *
     * @param createBy 发布者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取领取数量
     *
     * @return received - 领取数量
     */
    public Integer getReceived() {
        return received;
    }

    /**
     * 设置领取数量
     *
     * @param received 领取数量
     */
    public void setReceived(Integer received) {
        this.received = received;
    }

    /**
     * 获取使用数量
     *
     * @return consumed - 使用数量
     */
    public Integer getConsumed() {
        return consumed;
    }

    /**
     * 设置使用数量
     *
     * @param consumed 使用数量
     */
    public void setConsumed(Integer consumed) {
        this.consumed = consumed;
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
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取是否指定用户
     *
     * @return assign - 是否指定用户
     */
    public Boolean getAssign() {
        return assign;
    }

    /**
     * 设置是否指定用户
     *
     * @param assign 是否指定用户
     */
    public void setAssign(Boolean assign) {
        this.assign = assign;
    }

    /**
     * 获取代金券类型 1.新注册用户 2.报考用户 3.邀请用户 4.被邀请用户，如果支持多个，采取位运算或
     *
     * @return coupon_type - 代金券类型 1.新注册用户 2.报考用户 3.邀请用户 4.被邀请用户，如果支持多个，采取位运算或
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     * 设置代金券类型 1.新注册用户 2.报考用户 3.邀请用户 4.被邀请用户，如果支持多个，采取位运算或
     *
     * @param couponType 代金券类型 1.新注册用户 2.报考用户 3.邀请用户 4.被邀请用户，如果支持多个，采取位运算或
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    @Transient
    private Page page;
    @Transient
    private String activityName;
    @Transient
    private boolean flag1;
    @Transient
    private boolean flag2;
    @Transient
    private boolean flag3;
    @Transient
    private boolean flag4;
    @Transient
    private Date activityStart;
    @Transient
    private Date activityEnd;
    @Transient
    private String creator;


    public boolean getFlag1() {
        if (getCouponType() == null) {
            return false;
        }
        if((getCouponType()&1)==1){
            this.flag1 = true;
        }else{
            this.flag1=false;
        }
        return flag1;
    }

    public boolean getFlag2() {
        if (getCouponType() == null) {
            return false;
        }
        if ((getCouponType()&2) == 2) {
            this.flag2 = true;
        } else {
            this.flag2 = false;
        }
        return flag2;
    }
    public boolean getFlag3() {
        if (getCouponType() == null) {
            return false;
        }
        if ((getCouponType()&4) == 4) {
            this.flag3 = true;
        } else {
            this.flag3 = false;
        }
        return flag3;
    }

    public boolean getFlag4() {
        if (getCouponType() == null) {
            return false;
        }
        if ((getCouponType()&8) == 8) {
            this.flag4 = true;
        } else {
            this.flag4 = false;
        }
        return flag4;
    }
}