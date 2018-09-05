package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "AddSelfExamCartReq",description="F1-F4考试：添加考试到购物车中")
public class AddSelfExamCartReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "考试id")
	private Long examId;
	@ApiModelProperty(value = "考点id")
	private Long examPlaceId;
	@ApiModelProperty(value = "科目id")
	private Long examCourseId;
	@ApiModelProperty(value = "科目id")
	private Long examVersionId;
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.examId==null || this.examId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(this.examPlaceId ==null || this.examPlaceId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(this.examCourseId ==null || this.examCourseId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}


	public Long getExamId() {
		return examId;
	}


	public void setExamId(Long examId) {
		this.examId = examId;
	}


	public Long getExamPlaceId() {
		return examPlaceId;
	}


	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}


	public Long getExamCourseId() {
		return examCourseId;
	}


	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}


	public Long getExamVersionId() {
		return examVersionId;
	}


	public void setExamVersionId(Long examVersionId) {
		this.examVersionId = examVersionId;
	}


}
