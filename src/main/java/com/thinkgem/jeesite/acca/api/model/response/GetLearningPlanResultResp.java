package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 学习规划结果
 * @author Young
 * @version 2016-08-13
 */
public class GetLearningPlanResultResp extends BaseObjResponse {

	@ApiModelProperty(value = "学习规划结果",required=true)
	private List<LearningPlanInfo> learningPlanList;

	public GetLearningPlanResultResp(Integer respCode) {
		super(respCode);
	}

	public GetLearningPlanResultResp(List<LearningPlanInfo> learningPlanList) {
		super(RespConstants.GLOBAL_SUCCESS);
		this.learningPlanList = learningPlanList;
	}

	public List<LearningPlanInfo> getLearningPlanList() {
		return learningPlanList;
	}

	public void setLearningPlanList(List<LearningPlanInfo> learningPlanList) {
		this.learningPlanList = learningPlanList;
	}

}
