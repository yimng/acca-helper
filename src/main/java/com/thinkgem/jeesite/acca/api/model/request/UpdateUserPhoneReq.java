package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UpdateUserPhoneReq",description="修改用户登录手机号")
public class UpdateUserPhoneReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "phone",required=true)
    private String phone;

    @ApiModelProperty(value = "smsVcode", required = true)
    private String smsvcode;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsvcode() {
        return smsvcode;
    }

    public void setSmsvcode(String smsvcode) {
        this.smsvcode = smsvcode;
    }
}
