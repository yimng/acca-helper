package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 代注册上传支付凭证参数
 * @author Young
 * @version 2016/8/10
 */
@ApiModel(value = "代注册上传支付凭证")
public class SaveAccaRegisterPayReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "代注册id",required = true)
    private Long accaRegisterId;
    @ApiModelProperty(value = "支付凭证截图id")
    private Long payImgId;		// 支付凭证截图id

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }
        if (accaRegisterId == null || payImgId == null) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }

    public Long getAccaRegisterId() {
        return accaRegisterId;
    }

    public void setAccaRegisterId(Long accaRegisterId) {
        this.accaRegisterId = accaRegisterId;
    }

    public Long getPayImgId() {
        return payImgId;
    }

    public void setPayImgId(Long payImgId) {
        this.payImgId = payImgId;
    }

}
