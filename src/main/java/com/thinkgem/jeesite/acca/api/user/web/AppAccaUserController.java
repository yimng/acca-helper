/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.web;

import com.bcloud.msg.http.HttpSender;
import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.user.entity.*;
import com.thinkgem.jeesite.acca.api.user.service.AppAccaUserService;
import com.thinkgem.jeesite.acca.api.user.service.AppSmsVcodeService;
import com.thinkgem.jeesite.acca.web.coupon.service.CouponService;
import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteReward;
import com.thinkgem.jeesite.acca.web.user.service.InviteService;
import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.acca.api.model.response.AccaConfInfo;
import com.thinkgem.jeesite.acca.api.model.response.article.AppArticleCollectDto;
import com.thinkgem.jeesite.acca.constant.Constants;

import java.util.List;

/**
 * AppAccaUserController
 * @author Ivan
 * @version 2016-08-09
 */
@Api(value = "${apiPath}/user", description = "用户模块")
@Controller
@RequestMapping(value = "${apiPath}/user")
public class AppAccaUserController extends BaseController {

	@Autowired
	private AppAccaUserService appAccaUserService;
	
	@Autowired
	private AppSmsVcodeService appSmsVcodeService;

	@Autowired
    private InviteService appInviteService;

	@Autowired
    private CouponService couponService;

	@ApiOperation(value = "test验证码", httpMethod = "POST", notes = "获取短信验证码")
	@RequestMapping(value = "getTestVcode.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<String> getTestVcode(@RequestBody GetSmsVcodeReq req) {
		
		String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
		String account = "nanjing888";// 账号
		String pswd = "Tch10251025";// 密码
		//String mobile = "13856050245";// 手机号码，多个号码使用","分割
		String msg = "您好，您的验证码是";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = "19733";// 扩展码

		String returnString="";
		String vcode = RandomStringUtils.randomNumeric(4);
		
		try {
			returnString = HttpSender.batchSend(url, account, pswd, req.getPhone(), msg+vcode, needstatus, "",extno);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
		BaseObjResponse<String> resp = new BaseObjResponse<String>(RespConstants.GLOBAL_SUCCESS,returnString);
		
		return resp;
	}
	
	
	@ApiOperation(value = "获取短信验证码", httpMethod = "POST", notes = "获取短信验证码")
	@RequestMapping(value = "getSmsVcode.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseResponse getSmsVcode(@RequestBody GetSmsVcodeReq req) {
		
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseResponse(req.isCorrectParams());
		}
		
		// 是否是注册用户
		AppAccaUser accaUser = appAccaUserService.getAccaUserByPhone(req.getPhone());
		if(accaUser!=null && accaUser.getUserStatus()==Constants.AccaUserStatus.frozen){
			logger.info("login，账号被冻结：{}", req.getPhone());
			return new BaseResponse(RespConstants.USER_RREEZED);
		}
		BaseResponse resp = appSmsVcodeService.getSmsVcode(req.getPhone());
		
		return resp;
	}

	@ApiOperation(value = "验证手机验证码", notes = "验证手机验证码")
	@RequestMapping(value = "verifySmsVcode.do",method=RequestMethod.POST)
	public @ResponseBody BaseResponse verifySmsVcode(@RequestBody LoginReq req) {
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppAccaUser>(req.isCorrectParams());
		}
		return appAccaUserService.verifySmsVcode(req.getPhone(), req.getSmsVcode(), req.getDeviceId());
	}
	
	@ApiOperation(value = "app登陆",  notes = "app登陆")
	@RequestMapping(value = "login.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppAccaUser> login(@RequestBody LoginReq req){
		
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppAccaUser>(req.isCorrectParams());
		}
		
		return appAccaUserService.fastlogin(req.getPhone(),req.getSmsVcode(),req.getDeviceId());
	}

    @ApiOperation(value = "用户名密码注册",  notes = "用户名密码注册")
    @RequestMapping(value = "register.do",method=RequestMethod.POST)
    public @ResponseBody BaseObjResponse<AppAccaUser> register(@RequestBody LoginWithPasswordReq req){

        if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
            return new BaseObjResponse<>(req.isCorrectParams());
        }

        return appAccaUserService.register(req.getPhone(),req.getPassword(),req.getDeviceId());
    }

    @ApiOperation(value = "用户名密码登陆",  notes = "用户名密码登陆")
    @RequestMapping(value = "loginWithPassword.do",method=RequestMethod.POST)
    public @ResponseBody BaseObjResponse<AppAccaUser> loginWithPassword(@RequestBody LoginWithPasswordReq req){

        if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
            return new BaseObjResponse<>(req.isCorrectParams());
        }

        return appAccaUserService.loginWithPassword(req.getPhone(),req.getPassword(),req.getDeviceId());
    }
	
	@ApiOperation(value = "获取用户信息",  notes = "获取用户信息")
	@RequestMapping(value = "getUserInfo.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppAccaUser> getUserInfo(@RequestBody BaseRequest req){
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<>(req.isCorrectParams());
		}
		
		return appAccaUserService.getUserInfo(req.getAppUserId());
	}

	@ApiOperation(value = "判断用户是否设置了密码", notes = "判断用户是否设置了密码")
	@RequestMapping(value = "getUserPassword.do", method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<Boolean> getUserPassword(@RequestBody BaseRequest req) {
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<>(req.isCorrectParams());
		}
		return appAccaUserService.getPassword(req.getAppUserId());
	}
	
	@ApiOperation(value = "修改用户信息",  notes = "修改用户信息")
	@RequestMapping(value = "updateUserInfo.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppAccaUser> updateUserInfo(@RequestBody UpdateUserInfoReq req){
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<>(req.isCorrectParams());
		}
		
		return appAccaUserService.updateUserInfo(req);
	}

    @ApiOperation(value = "修改用户登录密码",  notes = "修改用户登录密码")
    @RequestMapping(value = "updateUserPassword.do",method=RequestMethod.POST)
    public @ResponseBody BaseObjResponse<AppAccaUser> updateUserPassword(@RequestBody UpdateUserPasswordReq req){
        if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
            return new BaseObjResponse<>(req.isCorrectParams());
        }

        return appAccaUserService.updateUserPassword(req);
    }

	@ApiOperation(value = "重置用户登录密码",  notes = "重置用户登录密码")
	@RequestMapping(value = "resetUserPassword.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppAccaUser> resetUserPassword(@RequestBody ResetUserPasswordReq req){
		String phone = req.getPhone();
		AppAccaUser user = appAccaUserService.getAccaUserByPhone(phone);
		if (user == null) {
			return new BaseObjResponse(RespConstants.USER_NONEXIST);
		}
		return appAccaUserService.resetUserPassword(user, req.getPassword());
	}

    @ApiOperation(value = "修改用户登录手机号",  notes = "修改用户登录手机号")
    @RequestMapping(value = "updateUserPhone.do",method=RequestMethod.POST)
    public @ResponseBody BaseObjResponse<AppAccaUser> updateUserPhone(@RequestBody UpdateUserPhoneReq req){
        if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
            return new BaseObjResponse<AppAccaUser>(req.isCorrectParams());
        }

        return appAccaUserService.updateUserPhone(req);
    }
	
	@ApiOperation(value = "获取acca俱乐部列表",  notes = "获取acca俱乐部列表")
	@RequestMapping(value = "getAccaClubList.do",method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppAccaClub> getAccaClubList(@RequestBody BaseRequest req){
		
		
		return appAccaUserService.getAccaClubList(req);
	}
	
	@ApiOperation(value = "修改用户地理位置",  notes = "修改用户地理位置")
	@RequestMapping(value = "updateUserLocation.do",method=RequestMethod.POST)
	public @ResponseBody BaseResponse updateUserLocation(@RequestBody UpdateUserLocationReq req){
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppAccaUser>(req.isCorrectParams());
		}
		
		return appAccaUserService.updateUserLocation(req);
	}
	
	@ApiOperation(value = "用户反馈信息",  notes = "用户反馈信息")
	@RequestMapping(value = "feedback.do",method=RequestMethod.POST)
	public @ResponseBody BaseResponse feedback(@RequestBody FeedbackReq req){
		if (req.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppAccaUser>(req.isCorrectParams());
		}
		
		return appAccaUserService.feedback(req);
	}
	
	
	@ApiOperation(value = "获取apk升级信息",  notes = "获取apk升级信息")
	@RequestMapping(value = "getApkUpdateInfo.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppApkVersion> getApkUpdateInfo(@RequestBody BaseRequest req){
		
		
		return appAccaUserService.getApkUpdateInfo();
	}
	
	@ApiOperation(value = "获取配置信息,无需传用户登录信息",  notes = "获取配置信息,无需传登录信息")
	@RequestMapping(value = "getAccaConfigInfo.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AccaConfInfo> getAccaConfigInfo(@RequestBody BaseRequest req){
		
		
		return appAccaUserService.getAccaConfigInfo();
	}

	@ApiOperation(value = "获取用户收藏文章列表",  notes = "获取用户收藏文章列表")
	@RequestMapping(value = "getCollectArticleList.do",method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppArticleCollectDto> getCollectArticleList(@RequestBody BasePageRequest req){
		int resp = req.isCorrectParams(true);
		if (resp!=RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppArticleCollectDto>(resp);
		}
		
		return appAccaUserService.getCollectArticleList(req.getPage(),req.getAppUser());
	}
	
	@ApiOperation(value = "批量删除收藏文章",  notes = "批量删除收藏文章")
	@RequestMapping(value = "deleteCollectBatch.do",method=RequestMethod.POST)
	public @ResponseBody BaseResponse deleteCollectBatch(@RequestBody DeleteCollectBatchReq req){
		int resp = req.isCorrectParams();
		if (resp!=RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppArticleCollectDto>(resp);
		}
		
		return appAccaUserService.deleteCollectBatch(req.getArticleIdList(),req.getAppUser());
	}
	@ApiOperation(value = "发送邀请人被邀请人手机号和代金券信息", notes = "发送邀请人被邀请人手机号和代金券信息")
	@RequestMapping(value = "invite.do", method = RequestMethod.POST)
	@CrossOrigin
	public @ResponseBody BaseResponse invite(@RequestBody InviteReq req) {

        // 验证手机号码
        if (!AppUtils.isMobileNum(req.getInviterPhone())) {
            return new BaseResponse(RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR);
        }
        if (!AppUtils.isMobileNum(req.getInviteePhone())) {
            return new BaseResponse(RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR);
        }

        int availableCoupons = couponService.getAvailableCouponsByCouponId(req.getCouponId());
        if (availableCoupons <= 0) {
            return new BaseResponse(RespConstants.COUPON_NOT_AVAILABLE);
        }
        //验证被邀请人的手机号没有被注册过，并且被邀请人的手机号没有在被邀请中
		String inviteePhone = req.getInviteePhone();
		AppAccaUser invitee = appAccaUserService.getAccaUserByPhone(inviteePhone);
        if (invitee != null){
			return new BaseResponse(RespConstants.USER_EXIST);
		}
		boolean inviteStatus = appInviteService.getInviteStatus(inviteePhone);
        // 被邀请人正在被邀请
        if (inviteStatus) {
        	return new BaseResponse(RespConstants.USER_WAS_INVITING);
		}
		return appInviteService.invite(req);

	}

	@ApiOperation(value = "根据邀请状态获得邀请列表", notes = "根据邀请状态获得邀请列表")
    @RequestMapping(value = "getInvitesByStatus.do", method = RequestMethod.POST)
	@CrossOrigin
	public @ResponseBody BasePageResponse<Invite> getInvitesByStatus(@RequestBody InviteStatusReq req) {
		int resp = req.isCorrectParams();
		if (resp != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse(resp);
		}
		String phone = req.getAppUser().getPhone();
		Invite invite = new Invite();
		invite.setInviterPhone(phone);
		List<Invite> page = appInviteService.findPage(invite, req.getStatus(), req.getPage().getStartIndex(), req.getPage().getPageSize());
		return new BasePageResponse(page);
	}
	@ApiOperation(value = "获得邀请排名", notes = "获得邀请排名")
    @RequestMapping(value = "getInviteRewardsRank.do", method = RequestMethod.POST)
	@CrossOrigin
	public @ResponseBody BasePageResponse<InviteReward> getInviteRewardsRank(@RequestBody BasePageRequest req) {
        int resp = req.isCorrectParams();
        if (resp != RespConstants.GLOBAL_SUCCESS) {
            return new BasePageResponse(resp);
        }
        List<InviteReward> rank = appInviteService.findInviteRewardsRank(req.getPage().getStartIndex(), req.getPage().getPageSize());
        return new BasePageResponse<>(rank);
    }
	
}