package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("重置用户密码")
public class ResetUserPasswordReq {

    @ApiModelProperty("需要重置的手机号")
    private String phone;
    @ApiModelProperty("新的密码")
    private String password;
}
