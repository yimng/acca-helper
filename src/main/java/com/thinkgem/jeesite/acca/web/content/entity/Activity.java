package com.thinkgem.jeesite.acca.web.content.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tbl_activity")
public class Activity implements Serializable {
    @Id
    @Column(name = "activity_id")
    @GeneratedValue(generator = "JDBC")
    private Long activityId;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "begin_time")
    private Date beginTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "activity_type")
    private Integer activityType;

    @Column(name = "activity_desc")
    private String activityDesc;

    private static final long serialVersionUID = 1L;

    /**
     * @return activity_id
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * @param activityId
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * @return activity_name
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * @return begin_time
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return activity_type
     */
    public Integer getActivityType() {
        return activityType;
    }

    /**
     * @param activityType
     */
    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    /**
     * @return activity_desc
     */
    public String getActivityDesc() {
        return activityDesc;
    }

    /**
     * @param activityDesc
     */
    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }
}