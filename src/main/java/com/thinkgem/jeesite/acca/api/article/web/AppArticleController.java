/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.web;

import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleCategory;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleComment;
import com.thinkgem.jeesite.acca.api.article.entity.AppAward;
import com.thinkgem.jeesite.acca.api.article.service.AppArticleService;
import com.thinkgem.jeesite.acca.api.model.request.GetArticleListByCategoryReq;
import com.thinkgem.jeesite.acca.api.model.request.GetArticleListByTitleAndCategoryReq;
import com.thinkgem.jeesite.acca.api.model.request.GetArticleListReq;
import com.thinkgem.jeesite.acca.api.model.request.GetTeacherInfoReq;
import com.thinkgem.jeesite.acca.api.model.response.GetTeacherInfoResp;
import com.thinkgem.jeesite.acca.api.model.response.SubmitAwardResp;
import com.thinkgem.jeesite.acca.api.model.response.SubmitCommentResp;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.service.AppAccaUserService;
import com.thinkgem.jeesite.acca.api.user.service.AppSmsVcodeService;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * AppCommonController
 * 
 * @author Ivan
 * @version 2016-08-09
 */
@Api(value = "${apiPath}/article", description = "article文章模块")
@Controller
@RequestMapping(value = "${apiPath}/article")
public class AppArticleController extends BaseController {

	@Autowired
	private AppArticleService appArticleService;
	@Autowired
	private AppSmsVcodeService appSmsVcodsaeService;
	@Autowired
	private AppAccaUserService appAccaUserService;

	@ApiOperation(value = "获取文章分类列表", notes = "获取文章分类列表")
	@RequestMapping(value = "getArticleCategoryList.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticleCategory> getArticleCategoryList(@RequestBody BaseRequest req) {

		return appArticleService.getArticleCategoryList(new AppArticleCategory());
	}
	
	@ApiOperation(value = "按文章标题模糊查询", notes = "按文章标题模糊查询")
	@RequestMapping(value = "getArticlesByTitle.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticle> getArticleCategoryList(@RequestBody GetArticleListByTitleAndCategoryReq req) {
		return appArticleService.getArticleListByTitleAndCategory(req);
	}

	@ApiOperation(value = "获取达人分享文件列表/获取名师指导文章列", notes = "获取达人分享文件列表/获取名师指导文章列")
	@RequestMapping(value = "getArticleList.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticle> getArticleList(@RequestBody GetArticleListReq req) {

		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppArticle>(req.isCorrectParams());
		}
		return appArticleService.getArticleList(req);
	}

	@ApiOperation(value = "按照分类获取有资有料文章列表", notes = "按照分类获取有资有料文章列表")
	@RequestMapping(value = "getArticleListByCategory.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticle> getArticleListByCategory(
			@RequestBody GetArticleListByCategoryReq req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppArticle>(req.isCorrectParams());
		}
		return appArticleService.getArticleListByCategory(req);
	}

	@ApiOperation(value = "获取名师详情及分享列表页面", notes = "获取名师详情及分享列表页面")
	@RequestMapping(value = "getTeacherInfo.do", method = RequestMethod.POST)
	public @ResponseBody GetTeacherInfoResp getTeacherInfo(@RequestBody GetTeacherInfoReq req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new GetTeacherInfoResp(req.isCorrectParams());
		}
		return appArticleService.getTeacherInfo(req);
	}

	@ApiOperation(value = "首页-获取文章列表", notes = "首页-获取文章列表")
	@RequestMapping(value = "getHomeArticleList.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticle> getHomeArticleList(@RequestBody BasePageRequest req) {
		/*
		 * if(req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){ return new
		 * BasePageResponse<AppArticle> (req.isCorrectParams()); }
		 */
		return appArticleService.getHomeArticleList(req);
	}

	// *********************************************************
	// ***************下面h5页面接口
	// *********************************************************
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(@RequestParam(value = "accaUserId", required = false, defaultValue = "0") Long accaUserId,
			AppArticle article, Model model) {
		// model.addAttribute("list", areaService.findAll());
		logger.info("article:" + article);
		return appArticleService.detail(accaUserId, article, model);
	}

	@ApiOperation(value = "用户验证", httpMethod = "POST", notes = "用户验证")
	@RequestMapping(value = "getVcode.do", method = RequestMethod.POST)
	public @ResponseBody BaseResponse getVcode(@RequestParam(value = "phone") String phone) {
		// 是否是注册用户
		
		
//		Enumeration paramNames = request.getParameterNames();
//		while (paramNames.hasMoreElements()) {
//			String paramName = (String) paramNames.nextElement();
//			String[] paramValues = request.getParameterValues(paramName);
//			if (paramValues.length == 1) {
//				String paramValue = paramValues[0];
//				if (paramValue.length() != 0) {
//					System.out.println("参数：" + paramName + "=" + paramValue);
//					// map.put(paramName, paramValue);
//				}
//			}
//		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String  value = request.getHeader("referer");
		System.out.println("===referer:" + value + " | "+ phone);
		AppAccaUser accaUser = appAccaUserService.getAccaUserByPhone(phone);
		if (accaUser != null && accaUser.getUserStatus() == Constants.AccaUserStatus.frozen) {
			logger.info("login，账号被冻结：{}", phone);
			return new BaseResponse(RespConstants.USER_RREEZED);
		}
		if(accaUser == null && value !=null ){
			if ("http://app.china-ife.com/acca/api/article/getVcode.do".equals(value)) {
				logger.info("login，您已被网警监控，累计50万次即追究刑事责任！：{}", phone);
				return new BaseResponse(RespConstants.USER_MUSTAPP);
			}
		}
		System.out.println("+++++++++++" + value + " | "+ phone);
//		Enumeration enumeration = request.getHeaderNames();
//		while(enumeration.hasMoreElements()){
//			String	s = (String) enumeration.nextElement();
//			String  value= request.getHeader(s);
//			System.out.println("参数：" + s + "=" + value);			
//		}			
//		BaseResponse resp = appSmsVcodeService.getSmsVcode(phone);
//		return resp;
		return null;
	}

	@ApiOperation(value = "用户验证", httpMethod = "POST", notes = "用户验证")
	@RequestMapping(value = "chekUser.do", method = RequestMethod.POST)
	public @ResponseBody BaseResponse chekUser(@RequestParam(value = "phone") String phone,
			@RequestParam(value = "smsVcode") String smsVcode) {
		// 是否是注册用户
		System.out.println("==============================chekUser "+phone+"=============================");
		AppAccaUser accaUser = appAccaUserService.getAccaUserByPhone(phone);
		if(accaUser!=null && accaUser.getUserStatus()==Constants.AccaUserStatus.frozen){
			logger.info("login，账号被冻结：{}", phone);			
			return new BaseResponse(RespConstants.USER_RREEZED);
		}
//		if(accaUser==null){
//			logger.info("login，账号被冻结：{}", phone);			
//			return new BaseResponse(RespConstants.USER_RREEZED);
//		}		
		return appAccaUserService.fastlogin(phone, smsVcode, "");
	}

	@ApiOperation(value = "记录分享", notes = "记录分享")
	@RequestMapping(value = "addActiveLog.do", method = RequestMethod.POST)
	public @ResponseBody String addActiveLog(@RequestParam(value = "activeId") Long activeId,
			@RequestParam(value = "phone") String phone) {
		StringBuffer sb = new StringBuffer();
		return appArticleService.addActiveLog(activeId, phone);
	}

	@ApiOperation(value = "获取当日抽奖资格", notes = "获取当日抽奖资格")
	@RequestMapping(value = "getUserAwardNum.do", method = RequestMethod.POST)
	public @ResponseBody String getUserAwardNum(@RequestParam(value = "activeId") Long activeId,
			@RequestParam(value = "phone") String phone) {
		StringBuffer sb = new StringBuffer();
		if (activeId == 0) {
			activeId = 1L;
		}
		Long num = appArticleService.getUserAwardNum(activeId, phone);
		sb.append("{\"respCode\":").append(num).append(",\"respDesc\":\"每人每天有一次抽奖机会，分享本活动有可能获得更多抽奖机会！\"}");
		return sb.toString();
	}

	@ApiOperation(value = "获取获奖名单", notes = "获取获奖名单")
	@RequestMapping(value = "getAwardList.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppAward> getAwardList(@RequestParam(value = "activeId") Long activeId) {
		if (activeId == 0) {
			activeId = 1L;
		}
		return appArticleService.getAwardList(activeId);
	}

	@ApiOperation(value = "用户奖项列表", notes = "用户奖项列表")
	@RequestMapping(value = "getUserAwardList.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppAward> getUserAwardList(@RequestParam(value = "activeId") Long activeId,
			@RequestParam(value = "phone") String phone) {
		System.out.print(phone);
		if (activeId == 0) {
			activeId = 1L;
		}
		return appArticleService.getAwardListByuser(activeId, phone);
	}

	@ApiOperation(value = "领取优惠券", notes = "领取优惠券")
	@RequestMapping(value = "getCaicuiAward.do", method = RequestMethod.POST)
	public @ResponseBody String getCaicuiAward(@RequestParam(value = "phone") String phone,
			@RequestParam(value = "cardRuleId") String cardRuleId) {
		return appArticleService.setAwardToCAicui(phone, cardRuleId);
	}

	@ApiOperation(value = "转盘抽奖", httpMethod = "POST", notes = "转盘抽奖")
	@RequestMapping(value = "getAward.do", method = RequestMethod.POST)
	public @ResponseBody SubmitAwardResp getAward(@RequestParam(value = "activeId") Long activeId,
			@RequestParam(value = "phone") String phone) {
		/*
		 * if(req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){ return new
		 * BasePageResponse<AppArticle> (req.isCorrectParams()); }
		 */
		System.out.println(phone);
		if (activeId == 0) {
			activeId = 1L;
		}
		return appArticleService.submitAward(activeId, phone);
	}

	@ApiOperation(value = "点赞/取消点赞接口", notes = "点赞/取消点赞接口")
	@RequestMapping(value = "praise.do", method = RequestMethod.POST)
	public @ResponseBody BaseObjResponse<String> praise(@RequestParam(value = "articleId") Long articleId,
			@RequestParam(value = "accaUserId") Long accaUserId,
			@RequestParam(value = "praiseType") Integer praiseType) {

		return appArticleService.praise(articleId, accaUserId, praiseType);
	}

	@ApiOperation(value = "收藏/取消收藏", notes = "收藏/取消收藏")
	@RequestMapping(value = "collect.do", method = RequestMethod.POST)
	public @ResponseBody BaseResponse collect(@RequestParam(value = "articleId") Long articleId,
			@RequestParam(value = "accaUserId") Long accaUserId,
			@RequestParam(value = "collectType") Integer collectType) {

		return appArticleService.collect(articleId, accaUserId, collectType);
	}

	@ApiOperation(value = "分页获取评论列表", notes = "分页获取评论列表")
	@RequestMapping(value = "getCommentList.do", method = RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticleComment> getCommentList(
			@RequestParam(value = "articleId") Long articleId, @RequestParam(value = "startIndex") Integer startIndex,
			@RequestParam(value = "pageSize") Integer pageSize) {
		/*
		 * if(req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){ return new
		 * BasePageResponse<AppArticle> (req.isCorrectParams()); }
		 */
		PageApi page = new PageApi(pageSize, startIndex);
		return appArticleService.getCommentList(articleId, page);
	}

	@ApiOperation(value = "提交用户认证", notes = "提交用户认证")
	@RequestMapping(value = "submitAuthen.do", method = RequestMethod.POST)
	public @ResponseBody String submitAuthen(AppAccaUser req) {
		/*
		 * if(req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){ return new
		 * BasePageResponse<AppArticle> (req.isCorrectParams()); }
		 */
		Date n = new Date();
		req.setUpdateDate(n);
		req.setIszbg(1);
		System.out.print(req);
		return appArticleService.submitAuthen(req);
	}

	@ApiOperation(value = "提交评论", notes = "提交评论")
	@RequestMapping(value = "submitComment.do", method = RequestMethod.POST)
	public @ResponseBody SubmitCommentResp submitComment(AppArticleComment req) {
		/*
		 * if(req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){ return new
		 * BasePageResponse<AppArticle> (req.isCorrectParams()); }
		 */
		return appArticleService.submitComment(req);
	}

}