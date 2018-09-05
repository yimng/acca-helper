package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "UpdateUserLocationReq",description="修改用户地理位置参数")
public class UpdateUserLocationReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "定位的省份id",required=true)
	private Integer provinceId;
	@ApiModelProperty(value = "定位的城市id",required=true)
	private Integer cityId;
	@ApiModelProperty(value = "定位的省份名称",required=true)
	private String provinceName;
	@ApiModelProperty(value = "定位的城市名称",required=true)
	private String cityName;
	
	
	
	@Override
    public int isCorrectParams() {
		
		if(super.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){
			return super.isCorrectParams();
		}
		if(provinceId==null || provinceId==0L){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(cityId==null || cityId==0L){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.provinceName)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.cityName)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}



	public Integer getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}



	public Integer getCityId() {
		return cityId;
	}



	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}



	public String getProvinceName() {
		return provinceName;
	}



	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}



	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	
}
