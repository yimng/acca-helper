package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取日历提醒参数
 * @author Young
 * @version 2016/9/7
 */
@ApiModel(value = "获取日历提醒参数")
public class GetCalendarTipsReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "月份 yyyy-MM格式字符串",required = true)
    private String tipMonth;		// 提醒时间

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }
        if (StringUtils.isEmpty(tipMonth)) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }
        return RespConstants.GLOBAL_SUCCESS;
    }

    public String getTipMonth() {
        return tipMonth;
    }

    public void setTipMonth(String tipMonth) {
        this.tipMonth = tipMonth;
    }

}
