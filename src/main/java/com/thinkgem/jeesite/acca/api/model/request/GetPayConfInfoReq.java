package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 删除个人日历提醒参数
 * @author Young
 * @version 2016/9/7
 */
@ApiModel(value = "获取支付信息接口")
public class GetPayConfInfoReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型：1表示代注册，2表示代报考",required = true)
    private Integer type;
    @ApiModelProperty(value = "type=1时，传输代注册记录id（accaRegisterId字段），type=2时，传值具体的考试订单id（orderId字段）",required = true)
    private Long id;

    @Override
    public int isCorrectParams() {
    	int resp = super.isCorrectParams();
        if(resp!= RespConstants.GLOBAL_SUCCESS){
            return resp;
        }
        if(type==null || (type!=1 && type!=2)){
        	return RespConstants.GLOBAL_PARAM_ERROR;
        }
        if(id==null || id==0){
        	return RespConstants.GLOBAL_PARAM_ERROR;
        }
        
        return RespConstants.GLOBAL_SUCCESS;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
