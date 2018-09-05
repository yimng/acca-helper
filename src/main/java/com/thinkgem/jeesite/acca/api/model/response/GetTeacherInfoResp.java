package com.thinkgem.jeesite.acca.api.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;
import com.thinkgem.jeesite.acca.api.article.entity.AppTeacher;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;

public class GetTeacherInfoResp extends BasePageResponse<AppArticle>{
	
	@ApiModelProperty(value = "名师详情信息",required=true)
	private AppTeacher teacher;
	
	public GetTeacherInfoResp(Integer respCode) {
		super(respCode);
	}
	
	public GetTeacherInfoResp(AppTeacher teacher,List<AppArticle> list) {
		super(list);
		this.teacher = teacher;
	}

	public AppTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(AppTeacher teacher) {
		this.teacher = teacher;
	}
	
	
}
