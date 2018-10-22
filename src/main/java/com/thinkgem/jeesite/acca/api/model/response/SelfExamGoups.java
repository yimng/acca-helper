package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class SelfExamGoups {
    public SelfExamGoups(Long placeId, String examDetailAddress, String examPlaceName, String examPlaceSn, List<AppExamSelfCart>list, double total) {
        this.placeId = placeId;
        this.examDetailAddress = examDetailAddress;
        this.examPlaceName = examPlaceName;
        this.examPlaceSn = examPlaceSn;
        this.list = list;
        this.total = total;
    }
    @ApiModelProperty(value = "自有考试考点Id",required=true)
    private Long placeId;
    @ApiModelProperty(value = "考点详细地址")
    private String examDetailAddress;		// 详细地址
    @ApiModelProperty(value = "考点名称")
    private String examPlaceName;		// 考点名称
    @ApiModelProperty(value = "考点编号")
    private String examPlaceSn;		// 考点编号

    @ApiModelProperty(value = "自有考试列表",required=true)
    private List<AppExamSelfCart> list;
    private double total;
}
