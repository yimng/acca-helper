package com.thinkgem.jeesite.acca.web.user.entity;

import com.thinkgem.jeesite.freetek.util.TimeUtils;
import lombok.Data;

import java.util.Date;

@Data
public class SmallCoupon {
    private Long id;
    private double price;
    private Date validityStart;
    private Date validityEnd;
    private String description;
    private Boolean canUse;
    private String validityDate;

    public String getValidityDate() {
        return TimeUtils.DateToStr(validityEnd, TimeUtils.sdfSimple);
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public Boolean getCanUse() {
        return validityEnd.getTime() > (new Date()).getTime() && validityStart.getTime() < (new Date()).getTime();
    }

    public void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }
}
