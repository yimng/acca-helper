/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;
import com.thinkgem.jeesite.acca.api.common.entity.AppConfHelp;
import com.thinkgem.jeesite.acca.api.common.entity.AppMobileDevice;
import com.thinkgem.jeesite.acca.api.common.entity.AppPayConf;
import com.thinkgem.jeesite.acca.api.common.service.AppCommonService;
import com.thinkgem.jeesite.acca.api.model.request.GetPayConfInfoReq;
import com.thinkgem.jeesite.acca.api.model.request.GetSmsVcodeReq;
import com.thinkgem.jeesite.acca.api.model.request.RegisterClientReq;
import com.thinkgem.jeesite.acca.api.model.request.SendMobileContantReq;
import com.thinkgem.jeesite.acca.api.model.response.GetPayConfInfoResp;

/**
 * AppCommonController
 * @author Ivan
 * @version 2016-08-09
 */
@Api(value = "${apiPath}/common", description = "common公有模块")
@Controller
@RequestMapping(value = "${apiPath}/common")
public class AppCommonController extends BaseController {

	
	@Autowired
	private AppCommonService appCommonService;
	
	
	
	@ApiOperation(value = "硬件设备注册接口", notes = "硬件设备注册接口")
	@RequestMapping(value = "registerClient.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppMobileDevice> registerClient(@RequestBody RegisterClientReq req) {
		
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppMobileDevice>(req.isCorrectParams());
		}
		
		return appCommonService.registerClient(req);
	}
	
	@ApiOperation(value = "手机通讯录上传接口", notes = "手机通讯录上传接口")
	@RequestMapping(value = "sendMobileContant.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseResponse sendMobileContant(@RequestBody SendMobileContantReq req){
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppMobileDevice>(req.isCorrectParams());
		}
		
		return appCommonService.sendMobileContant(req.getDeviceId(),req.getList());
	}
	
	/*@ApiOperation(value = "获取支付账户信息", notes = "获取支付账户信息")
	@RequestMapping(value = "getPayConfInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppPayConf> getPayConfInfo(@RequestBody BaseRequest req){
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppPayConf>(req.isCorrectParams());
		}
		
		return appCommonService.getPayConfInfo();
	}*/
	
	
	@ApiOperation(value = "获取支付账户信息", notes = "获取支付账户信息")
	@RequestMapping(value = "getPayConfInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody GetPayConfInfoResp getPayConfInfo(@RequestBody GetPayConfInfoReq req){
		logger.info("GetPayConfInfoReq:{}",req);
		int resp = req.isCorrectParams();
		if (resp!=RespConstants.GLOBAL_SUCCESS) {
			return new GetPayConfInfoResp(resp);
		}
		
		return appCommonService.getPayConfInfo(req.getAppUser(),req.getType(),req.getId());
	}
	
	
	
	//*********************************************************
	//***************下面为页面接口,富文本配置页面，按照type获取
	//*********************************************************
	@RequestMapping(value = "confhelp/detail",method=RequestMethod.GET)
	public String confHelpDetail(AppConfHelp appConfHelp, Model model) {
		
		
		//model.addAttribute("list", areaService.findAll());
		
		logger.info("article:"+appConfHelp);
		
		return appCommonService.confHelpDetail(appConfHelp,model);
	}
	
}