package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("机考中心问题")
public class EcQuestionReq extends BaseRequest {
    @ApiModelProperty("问题名称")
    private String titile;
    @ApiModelProperty("机考中心ID")
    private Long examPlaceId;
    @ApiModelProperty("机考中心名称")
    private String examPlaceName;
}
