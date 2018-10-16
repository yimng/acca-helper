package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "InviteReq",description="用户邀请接口")
@Data
public class InviteReq {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "邀请人手机号")
    private String inviterPhone;
    @ApiModelProperty(value = "被邀请人手机号")
    private String inviteePhone;
    @ApiModelProperty(value = "代金券ID")
    private Long couponId;
}
