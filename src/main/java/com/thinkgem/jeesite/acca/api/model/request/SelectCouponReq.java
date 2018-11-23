package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("选择一个优惠券")
public class SelectCouponReq extends BaseRequest {

    @ApiModelProperty(value = "机考中心ID")
    private Long examPlaceId;
    @ApiModelProperty(value = "用户优惠券ID")
    private Long userCouponId;

    @Override
    public int isCorrectParams() {
        int resp = super.isCorrectParams();
        if(resp!= RespConstants.GLOBAL_SUCCESS){
            return resp;
        }

        if(this.examPlaceId==null || this.examPlaceId==0){
            return RespConstants.GLOBAL_PARAM_ERROR;
        }


        return RespConstants.GLOBAL_SUCCESS;
    }

}
