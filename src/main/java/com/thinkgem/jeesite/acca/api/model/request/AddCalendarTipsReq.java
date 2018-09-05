package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 日历提醒参数
 * @author Young
 * @version 2016/9/7
 */
@ApiModel(value = "日历提醒参数")
public class AddCalendarTipsReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提醒时间 yyyy-MM-dd hh:mm格式字符串",required = true)
    private String tipTime;		// 提醒时间
    @ApiModelProperty(value = "提醒的内容",required = true)
    private String tipContent;		// 提醒的内容
    @ApiModelProperty(value = "提醒设置,1:提前一天,2:提前一周",required = true)
    private Integer tipSet;		// 提醒设置

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }
        if (StringUtils.isEmpty(tipTime) || StringUtils.isEmpty(tipContent) || tipSet == null) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }

    public String getTipTime() {
        return tipTime;
    }

    public void setTipTime(String tipTime) {
        this.tipTime = tipTime;
    }

    public String getTipContent() {
        return tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public Integer getTipSet() {
        return tipSet;
    }

    public void setTipSet(Integer tipSet) {
        this.tipSet = tipSet;
    }
}
