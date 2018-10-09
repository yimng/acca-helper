package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class SelfExamGoups {
    public SelfExamGoups(Long placeId, List<AppExamSelfCart>list, double total) {
        this.placeId = placeId;
        this.list = list;
        this.total = total;
    }
    @ApiModelProperty(value = "自有考试考点Id",required=true)
    private Long placeId;
    @ApiModelProperty(value = "自有考试列表",required=true)
    private List<AppExamSelfCart> list;
    private double total;
}
