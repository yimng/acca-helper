package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("SeasonOffPlaceReq")
public class SeasonOffPlaceReq extends BaseRequest {

    private static final long serialVersionUID = 7143937094762391903L;

    @ApiModelProperty(value = "所选考试年月")
    private String examStartTimeStr;

    @ApiModelProperty(value = "所选考试城市")
    private String cityId;
}
