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
	
	@ApiModelProperty(value = "订单状态: 10: 待支付 20: 待审核 30: 审核通过 35: 补传信息 40: 审核失败 50: 已取消 60: 已删除")
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
