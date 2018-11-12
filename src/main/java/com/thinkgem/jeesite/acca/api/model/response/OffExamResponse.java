package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamCourse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;

import java.util.List;

public class OffExamResponse extends BasePageResponse<AppOfficialExamCourse> {

    private String lng;        // 经度
    private String lat;        // 纬度
    private String examPlaceContantName;        // 联系人
    private String examPlaceContantPhone;        // 联系电话

    public OffExamResponse(Integer respCode) {
        super(respCode);
    }

    public OffExamResponse(List<AppOfficialExamCourse> list, String lng, String lat, String examPlaceContantName, String examPlaceContantPhone) {
        super(list);
        this.lng = lng;
        this.lat = lat;
        this.examPlaceContantName = examPlaceContantName;
        this.examPlaceContantPhone = examPlaceContantPhone;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getExamPlaceContantName() {
        return examPlaceContantName;
    }

    public void setExamPlaceContantName(String examPlaceContantName) {
        this.examPlaceContantName = examPlaceContantName;
    }

    public String getExamPlaceContantPhone() {
        return examPlaceContantPhone;
    }

    public void setExamPlaceContantPhone(String examPlaceContantPhone) {
        this.examPlaceContantPhone = examPlaceContantPhone;
    }
}
