package com.thinkgem.jeesite.acca.api.exam.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("电话咨询查询考点信息")
public class AppSmallExamPlace extends DataEntity<AppSmallExamPlace> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "考点id")
    private Long examPlaceId;        // exam_place_id
    @ApiModelProperty(value = "考点所在城市id")
    private Integer examCityId;        // 城市id
    @ApiModelProperty(value = "考点所在城市名称")
    private String examCityName;        // 考点城市名称
    @ApiModelProperty(value = "考点详细地址")
    private String examDetailAddress;        // 详细地址
    @ApiModelProperty(value = "考点名称")
    private String examPlaceName;        // 考点名称
    @ApiModelProperty(value = "考点编号")
    private String examPlaceSn;        // 考点编号
    @ApiModelProperty(value = "考点联系人名称")
    private String examPlaceContantName;        // 联系人
    @ApiModelProperty(value = "考点联系人电话")
    private String examPlaceContantPhone;        // 联系电话

    public Long getExamPlaceId() {
        return examPlaceId;
    }

    public void setExamPlaceId(Long examPlaceId) {
        this.examPlaceId = examPlaceId;
    }

    public Integer getExamCityId() {
        return examCityId;
    }

    public void setExamCityId(Integer examCityId) {
        this.examCityId = examCityId;
    }

    public String getExamCityName() {
        return examCityName;
    }

    public void setExamCityName(String examCityName) {
        this.examCityName = examCityName;
    }

    public String getExamDetailAddress() {
        return examDetailAddress;
    }

    public void setExamDetailAddress(String examDetailAddress) {
        this.examDetailAddress = examDetailAddress;
    }

    public String getExamPlaceName() {
        return examPlaceName;
    }

    public void setExamPlaceName(String examPlaceName) {
        this.examPlaceName = examPlaceName;
    }

    public String getExamPlaceSn() {
        return examPlaceSn;
    }

    public void setExamPlaceSn(String examPlaceSn) {
        this.examPlaceSn = examPlaceSn;
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
