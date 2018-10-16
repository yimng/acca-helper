package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("邀请状态")
public class InviteStatusReq extends BasePageRequest {
    @ApiModelProperty("邀请状态 0:正在邀请 1:邀请成功 2:邀请失败")
    private String status;
}
