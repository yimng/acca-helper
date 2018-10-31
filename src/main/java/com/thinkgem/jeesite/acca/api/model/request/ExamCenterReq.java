package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("按城市搜索机考中心电话")
public class ExamCenterReq extends BasePageRequest {
    @ApiModelProperty(value = "城市id", required = false)
    private  Integer examCityId;
}
