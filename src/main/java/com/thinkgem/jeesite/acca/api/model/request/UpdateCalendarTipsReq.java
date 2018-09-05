package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改日历提醒参数
 * @author Young
 * @version 2016/9/9
 */
@ApiModel(value = "修改日历提醒参数")
public class UpdateCalendarTipsReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提醒id",required = true)
    private Long id;		// 提醒id
    @ApiModelProperty(value = "是否为系统消息,0为不是;1为是",required = true)
    private Integer isSys;
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
        //必填信息不能为空,并且,不能为系统消息
        if (StringUtils.isEmpty(tipTime) || StringUtils.isEmpty(tipContent) || tipSet == null || id == null || isSys == ApiConstant.TIP_IS_SYS) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }
}
