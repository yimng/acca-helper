package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.PageApi;

@ApiModel(value = "GetOrderListByStatisReq",description="订单列表请求参数")
public class GetOrderListByStatisReq extends BaseRequest {

	private static final long serialVersionUID = -6583174586189207509L;
	
	@ApiModelProperty(value = "acca学员id")
	private Long accaUserId;
	
	@ApiModelProperty(value = "订单状态")
	private Integer orderStatus;
	
	@ApiModelProperty(value = "分页参数",required=true)
	private PageApi page;

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PageApi getPage() {
		return page;
	}

	public void setPage(PageApi page) {
		this.page = page;
	}

}
