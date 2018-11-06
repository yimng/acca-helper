package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FeedbackReq", description = "用户反馈接口")
public class FeedbackReq extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "反馈类型Q:问题，S:建议")
    private String type;
    @ApiModelProperty(value = "所处分类")
    private Byte categoryId;
    @ApiModelProperty(value = "反馈人QQ,邮箱,手机号")
    private String phone;
    @ApiModelProperty(value = "反馈内容", required = true)
    private String content;
    @ApiModelProperty(value = "上传的图片")
    private Long imgId;


    public int isCorrectParams() {

        if (StringUtils.isEmpty(this.content)) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }
        if (this.content.length() > 256) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }
}
