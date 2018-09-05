package com.thinkgem.jeesite.freetek.api.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.service.AppAccaUserService;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("BaseRequest")
public class BaseRequest extends BaseModel {
	
	private final Logger log = LoggerFactory.getLogger(BaseRequest.class);
	private static final long serialVersionUID = 2482633119866726335L;
	
	private static AppAccaUserService appAccaUserService = SpringContextHolder.getBean(AppAccaUserService.class);
	
	@ApiModelProperty(value = "用户ID")
	private Long appUserId;
	@ApiModelProperty(value = "用户token,需要登录的接口必须带上此参数")
	private String userAccessToken;
	
	@JsonIgnore
	private AppAccaUser appUser;
	
	public int isCorrectParams() {
		if (appUserId == null) {
			log.debug("loanUserId不能为空");
			return RespConstants.USER_NONEXIST;
		}
		if (StringUtils.isEmpty(userAccessToken)) {
			log.debug("userAccessToken不能为空");
			return RespConstants.USER_NONEXIST;
		}
		AppAccaUser userInfo = appAccaUserService.get(new AppAccaUser(appUserId));
		if (userInfo == null 
				|| StringUtils.isEmpty(userInfo.getUserAccessToken()) 
				|| !userAccessToken.equals(userInfo.getUserAccessToken())) {
			log.debug("无效AccessToken：{}",userAccessToken);
			return RespConstants.USER_NONEXIST;
		}
		if (userInfo.getUserStatus() == null || userInfo.getUserStatus()==Constants.AccaUserStatus.frozen) {
			log.debug("用户已被冻结：{}",userInfo);
			return RespConstants.USER_RREEZED;
		}
		
		this.appUser = userInfo;
		
		return RespConstants.GLOBAL_SUCCESS;
	}

	

	public String getUserAccessToken() {
		return userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}



	public Long getAppUserId() {
		return appUserId;
	}



	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}



	public AppAccaUser getAppUser() {
		return appUser;
	}



	public void setAppUser(AppAccaUser appUser) {
		this.appUser = appUser;
	}

}
