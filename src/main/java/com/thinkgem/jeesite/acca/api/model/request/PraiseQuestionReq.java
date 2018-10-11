package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("评价问题")
public class PraiseQuestionReq extends BaseRequest {
    @ApiModelProperty("问题ID")
    private Long questionId;
    @ApiModelProperty("对问题的评价，0.有用 1. 内容太啰嗦2. 答案不清楚 3. 操作未解决 4. 其它问题")
    private Short praise;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Short getPraise() {
        return praise;
    }

    public void setPraise(Short praise) {
        this.praise = praise;
    }
}
