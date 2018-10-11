package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("获取热点问题")
public class GetHotQuestionReq extends BasePageRequest {

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    @ApiModelProperty("是否是热点问题")
    public boolean hot;

}
