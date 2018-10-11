package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("问题分类")
public class GetCategoryQuestion extends BasePageRequest {
    public Byte getCatergoryId() {
        return catergoryId;
    }

    public void setCatergoryId(Byte catergoryId) {
        this.catergoryId = catergoryId;
    }

    @ApiModelProperty("问题分类ID")
    private Byte catergoryId;
}
