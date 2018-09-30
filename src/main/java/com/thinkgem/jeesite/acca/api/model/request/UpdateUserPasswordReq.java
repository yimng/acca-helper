package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UpdateUserPasswordReq",description="修改用户登录密码")
public class UpdateUserPasswordReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "password",required=true)
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
