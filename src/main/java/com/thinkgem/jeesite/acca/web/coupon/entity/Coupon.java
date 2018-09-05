package com.thinkgem.jeesite.acca.web.coupon.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.annotation.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import java.util.Date;

/**
 * 代金券
 * @author Lukun
 * @version 2018.08.29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Coupon extends DataEntity<Coupon> {

    private static final long serialVersionUID = 1L;
    @Length(min = 1, max = 15, message = "活动名字在5-15字符之间")
    private String activityName;
    //@NotEmpty
    private Double price;
    //@NotEmpty
    private Date validityStart;
    @Future
    private Date validityEnd;
    //@NotEmpty
    private Integer cnt;
    private String description;
    private Date startTime;
    private Date endTime;
    private Integer received;
    private Integer consumed;
    private Boolean assign;
    private Integer couponType;

    private boolean flag1;
    private boolean flag2;
    private boolean flag3;
    private boolean flag4;
    private boolean published;
    private boolean prePublished;
    private boolean postPublished;

    public boolean getFlag1() {
        if (couponType == null) {
            return false;
        }
        if((couponType&1)==1){
            this.flag1 = true;
        }else{
            this.flag1=false;
        }
        return flag1;
    }

    public boolean getFlag2() {
        if (couponType == null) {
            return false;
        }
        if ((couponType&2) == 2) {
            this.flag2 = true;
        } else {
            this.flag2 = false;
        }
        return flag2;
    }

    public boolean getFlag3() {
        if (couponType == null) {
            return false;
        }
        if ((couponType&4) == 4) {
            this.flag3 = true;
        } else {
            this.flag3 = false;
        }
        return flag3;
    }

    public boolean getFlag4() {
        if (couponType == null) {
            return false;
        }
        if ((couponType&8) == 8) {
            this.flag4 = true;
        } else {
            this.flag4 = false;
        }
        return flag4;
    }
    public boolean getPublished() {
        Date date = new Date();
        this.published = date.after(startTime) && date.before(endTime);
        return published;
    }

    public boolean getPrePublished() {
        Date date = new Date();
        this.prePublished = date.before(startTime);
        return prePublished;
    }

    public boolean getPostPublished() {
        Date date = new Date();
        this.postPublished = date.after(endTime);
        return postPublished;
    }

}
