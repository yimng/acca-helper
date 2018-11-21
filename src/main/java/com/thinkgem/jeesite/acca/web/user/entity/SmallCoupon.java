package com.thinkgem.jeesite.acca.web.user.entity;

import com.sun.tools.internal.jxc.ap.Const;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import lombok.Data;

import java.util.Date;

@Data
public class SmallCoupon {
    private Long userCouponId;
    private Long userId;
    private Long couponId;
    private double price;
    private Date validityStart;
    private Date validityEnd;
    private String description;
    private Boolean validity;
    private String validityDate;
    private String status;

    public String getValidityDate() {
        return TimeUtils.DateToStr(validityEnd, TimeUtils.sdfSimple);
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public Boolean getValidity() {
        return validityEnd.getTime() > (new Date()).getTime()
                && validityStart.getTime() < (new Date()).getTime();
    }

    public void setValidity(Boolean validity) {
        this.validity =  validity;
    }
}
