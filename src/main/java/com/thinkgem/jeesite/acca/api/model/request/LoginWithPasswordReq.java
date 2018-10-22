package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
@Data
public class LoginWithPasswordReq extends BaseModel {
    @ApiModelProperty(value = "手机号",required=true)
    private String phone;

    @ApiModelProperty(value = "密码",required=true)
    private String password;

    @ApiModelProperty(value = "硬件设备注册id",required=false)
    private String deviceId;

    public int isCorrectParams() {

        if (StringUtils.isEmpty(phone)) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }
        if (StringUtils.isEmpty(password)) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }
        if (!AppUtils.isMobileNum(phone)) {
            return RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }
}
