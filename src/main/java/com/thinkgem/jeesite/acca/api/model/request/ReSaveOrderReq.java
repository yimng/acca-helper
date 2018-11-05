package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ReSaveOrderReq", description = "按照订单id重新提交支付凭证")
public class ReSaveOrderReq extends BaseRequest {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id", required = true)
    private Long orderId;
    @ApiModelProperty(value = "支付截图id", required = true)
    private Long orderPayImgId;
    @ApiModelProperty(value = "acca学员资料-姓名", required = true)
    private String registerName;
    @ApiModelProperty(value = "acca学员资料-身份证件号码", required = true)
    private String registerCardNumber;
    @ApiModelProperty(value = "acca学员资料-手机号", required = true)
    private String registerPhone;
    @ApiModelProperty(value = "acca学员资料-邮箱", required = true)
    private String registerEmail;
    @ApiModelProperty(value = "acca学员资料-二寸免冠证件照图片id", required = true)
    private Long registerWhiteColorImgId;
    @ApiModelProperty(value = "acca学员资料-acca账号", required = true)
    private String accaRegisterName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderPayImgId() {
        return orderPayImgId;
    }

    public void setOrderPayImgId(Long orderPayImgId) {
        this.orderPayImgId = orderPayImgId;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterCardNumber() {
        return registerCardNumber;
    }

    public void setRegisterCardNumber(String registerCardNumber) {
        this.registerCardNumber = registerCardNumber;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    public Long getRegisterWhiteColorImgId() {
        return registerWhiteColorImgId;
    }

    public void setRegisterWhiteColorImgId(Long registerWhiteColorImgId) {
        this.registerWhiteColorImgId = registerWhiteColorImgId;
    }

    public String getAccaRegisterName() {
        return accaRegisterName;
    }

    public void setAccaRegisterName(String accaRegisterName) {
        this.accaRegisterName = accaRegisterName;
    }
}
