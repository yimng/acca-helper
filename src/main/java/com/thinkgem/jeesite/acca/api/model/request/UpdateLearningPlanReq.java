package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 学习计划-修改学习规划请求参数
 * @author Young
 * @version 2016/8/12
 */
@ApiModel(value = "修改学习规划")
public class UpdateLearningPlanReq extends BaseRequest {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学习计划id",required = true)
    private String learningPlanId;
    @ApiModelProperty(value = "开始日期 yyyy-MM-dd格式的字符串",required = true)
    private String startDate;
    @ApiModelProperty(value = "结束日期 yyyy-MM-dd格式的字符串",required = true)
    private String endDate;

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }
        if (learningPlanId == null || StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }

    public String getLearningPlanId() {
        return learningPlanId;
    }

    public void setLearningPlanId(String learningPlanId) {
        this.learningPlanId = learningPlanId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
