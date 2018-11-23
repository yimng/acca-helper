package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单详情请求")
public class OrderDetailReq extends BaseRequest {
    @ApiModelProperty(required = false, value = "用户优惠券ID")
    private Long userCouponId;
}
