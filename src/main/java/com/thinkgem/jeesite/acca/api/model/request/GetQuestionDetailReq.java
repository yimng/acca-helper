package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("问题ID")
public class GetQuestionDetailReq extends BaseRequest {
    private static final long serialVersionUID = 6901956833747153759L;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @ApiModelProperty(value = "id", required = true)
    private Long questionId;
}
