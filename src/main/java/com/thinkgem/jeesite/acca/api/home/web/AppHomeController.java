package com.thinkgem.jeesite.acca.api.home.web;

import com.thinkgem.jeesite.acca.api.home.entity.AppBanner;
import com.thinkgem.jeesite.acca.api.home.entity.AppMsgPushLog;
import com.thinkgem.jeesite.acca.api.home.service.AppBannerService;
import com.thinkgem.jeesite.acca.api.home.service.AppMsgPushLogService;
import com.thinkgem.jeesite.acca.api.model.response.LearningTipsResp;
import com.thinkgem.jeesite.acca.api.plan.service.AppUserLearningPlanService;
import com.thinkgem.jeesite.acca.web.content.entity.Banner;
import com.thinkgem.jeesite.acca.web.content.service.BannerService;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页BannerController
 * @author Young
 * @version 2016-08-15
 */
@Api(value = "${apiPath}/home", description = "首页")
@Controller
@RequestMapping(value = "${apiPath}/home")
public class AppHomeController extends BaseController {

	@Autowired
	private AppBannerService appBannerService;
	@Autowired
	private AppMsgPushLogService appMsgPushLogService;
	@Autowired
	private AppUserLearningPlanService learningPlanService;
	@Autowired
	private BannerService bannerService;

	@ApiOperation(value = "首页-获取Banner列表", httpMethod = "POST", notes = "首页-获取Banner列表", position=1)
	@RequestMapping(value = "/getBannerList.do")
	public @ResponseBody
	BasePageResponse<AppBanner> getBannerList(@RequestBody BaseRequest req) {
		BasePageResponse<AppBanner> resp = appBannerService.getBannerList(req);
		return resp;
	}

	@ApiOperation(value = "首页-获取消息列表", httpMethod = "POST", notes = "首页-获取消息列表", position=2)
	@RequestMapping(value = "/getMsgList.do")
	public @ResponseBody BasePageResponse<AppMsgPushLog> getMsgList(@RequestBody BasePageRequest req) {
		BasePageResponse<AppMsgPushLog> resp = appMsgPushLogService.getMsgList(req);
		return resp;
	}

	@ApiOperation(value = "首页-获取未读消息条数,即消息小红点", httpMethod = "POST", notes = "首页-获取未读消息条数,即消息小红点", position=3)
	@RequestMapping(value = "/getUnReadMsgCount.do")
	public @ResponseBody
	BaseObjResponse<Long> getUnReadMsgCount(@RequestBody BaseRequest req) {
		BaseObjResponse<Long> resp = appMsgPushLogService.getUnReadMsgCount(req);
		return resp;
	}

	@ApiOperation(value = "首页-更新消息阅读状态", httpMethod = "POST", notes = "首页-更新消息阅读状态",position = 4)
	@RequestMapping(value = "/updateMsgReadStatus.do")
	public @ResponseBody BaseResponse updateMsgReadStatus(@RequestBody BaseObjRequest<Long> req) {
		BaseResponse resp = appMsgPushLogService.updateMsgReadStatus(req);
		return resp;
	}

	@ApiOperation(value = "首页-批量删除消息", httpMethod = "POST", notes = "首页-批量删除消息",position = 5)
	@RequestMapping(value = "/delBatchMsg.do")
	public @ResponseBody BaseResponse delBatchMsg(@RequestBody BaseBatchRequest<Long> req) {
		BaseResponse resp = appMsgPushLogService.delBatchMsg(req);
		return resp;
	}

	@ApiOperation(value = "首页-学习规划提醒", httpMethod = "POST", notes = "首页-学习规划提醒",position = 6)
	@RequestMapping(value = "/learningTips.do")
	public @ResponseBody
	LearningTipsResp learningTips(@RequestBody BaseRequest req) {
		LearningTipsResp resp = learningPlanService.getLearningTips(req);
		return resp;
	}

	/**
	 * 获取banner内容的静态页面
	 * @param banner 传过来的只有bannerId
	 * @return
	 */
	@RequestMapping(value = "/html",method = RequestMethod.GET)
	public String staticPage(Banner banner, Model model) {
		Banner appBanner = bannerService.get(banner);
		model.addAttribute("banner", appBanner);
		return "web/content/banner/bannerPage";
	}
}