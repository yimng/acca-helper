package com.thinkgem.jeesite.acca.api.plan.web;

import com.thinkgem.jeesite.acca.api.home.entity.AppTips;
import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.model.response.GetLearningPlanResultResp;
import com.thinkgem.jeesite.acca.api.plan.entity.AppAccaEnglishWord;
import com.thinkgem.jeesite.acca.api.plan.entity.AppExamCourse;
import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;
import com.thinkgem.jeesite.acca.api.plan.entity.PlanTips;
import com.thinkgem.jeesite.acca.api.plan.service.AppUserLearningPlanService;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 学习计划controller
 * @author Young
 * @version 2016-08-10
 */
@Api(value = "${apiPath}/plan", description = "学习计划模块")
@Controller
@RequestMapping(value = "${apiPath}/plan")
public class AppUserLearningPlanController extends BaseController {

	@Autowired
	private AppUserLearningPlanService appUserLearningPlanService;

	@ApiOperation(value = "学习计划－规划ACCA学习计划", httpMethod = "POST", notes = "学习计划－提交学习计划", position=2)
	@RequestMapping(value = "/submitLearningPlan.do")
	public @ResponseBody
	BasePageResponse<AppUserLearningPlan> submitLearningPlan(@RequestBody SubmitLearningPlanReq req){
		BasePageResponse<AppUserLearningPlan> resp = appUserLearningPlanService.submitLearningPlan(req);
		return resp;
	}

	@ApiOperation(value = "学习计划－获取学习计划列表", httpMethod = "POST", notes = "学习计划－获取学习计划列表", position=3)
	@RequestMapping(value = "/getLearningPlanList.do")
	public @ResponseBody
	BasePageResponse<AppUserLearningPlan> getLearningPlanList(@RequestBody BaseRequest req){
		BasePageResponse<AppUserLearningPlan> resp = appUserLearningPlanService.getLearningPlanList(req);
		return resp;
	}

	@ApiOperation(value = "学习计划－删除学习规划", httpMethod = "POST", notes = "学习计划－删除学习规划", position=4)
	@RequestMapping(value = "/delLearningPlan.do")
	public @ResponseBody
	BaseResponse delLearningPlan(@RequestBody BaseRequest req){
		BaseResponse resp = appUserLearningPlanService.deleteLearningPlan(req);
		return resp;
	}

	@ApiOperation(value = "学习计划－修改学习规划", httpMethod = "POST", notes = "学习计划－修改科目学习规划", position=5)
	@RequestMapping(value = "/updateLearningPlan.do")
	public @ResponseBody
	BaseObjResponse<AppUserLearningPlan> updateLearningPlan(@RequestBody UpdateLearningPlanReq req){
		BaseObjResponse<AppUserLearningPlan> resp = appUserLearningPlanService.updateLearningPlan(req);
		return resp;
	}

	@ApiOperation(value = "学习计划－获取所有考试科目列表", httpMethod = "POST", notes = "学习计划－获取所有考试科目列表", position=6)
	@RequestMapping(value = "/getExamPlanList.do")
	public @ResponseBody
	BasePageResponse<AppExamCourse> getExamPlanList(@RequestBody BaseRequest req){
		BasePageResponse<AppExamCourse> resp = appUserLearningPlanService.getExamPlanList(req);
		return resp;
	}

	/**
	 * 学习规划页面
	 * @return
	 */
	@ApiOperation(value = "学习计划－获取学习规划页面结果", httpMethod = "POST", notes = "学习计划－获取学习规划页面结果", position=7)
	@RequestMapping(value = "/getLeaningPlanResult.do",method = RequestMethod.POST)
	public @ResponseBody
	GetLearningPlanResultResp getLeaningPlanResult(@RequestBody BaseRequest req){
		return appUserLearningPlanService.getLeaningPlanResult(req);
	}

	/**
	 * 添加日历提醒
	 * @return
	 */
	@ApiOperation(value = "学习计划－添加日历提醒", httpMethod = "POST", notes = "学习计划－添加日历提醒", position=8)
	@RequestMapping(value = "/saveCalendarTips.do",method = RequestMethod.POST)
	public @ResponseBody
	BaseResponse saveCalendarTips(@RequestBody AddCalendarTipsReq req){
		if(req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS){
			return new BaseResponse(req.isCorrectParams());
		}
		return appUserLearningPlanService.addCalendarTips(req);
	}

	/**
	 * 删除个人日历提醒
	 * @return
	 */
	@ApiOperation(value = "学习计划－删除个人日历提醒", httpMethod = "POST", notes = "学习计划－删除个人日历提醒", position=9)
	@RequestMapping(value = "/delCalendarTips.do",method = RequestMethod.POST)
	public @ResponseBody
	BaseResponse delCalendarTips(@RequestBody DelCalendarTipsReq req){
		if(req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS){
			return new BaseResponse(req.isCorrectParams());
		}
		return appUserLearningPlanService.delCalendarTips(req);
	}

	/**
	 * 获取日历提醒
	 * @return
	 */
	@ApiOperation(value = "学习计划－获取日历提醒", httpMethod = "POST", notes = "学习计划－获取日历提醒", position=10)
	@RequestMapping(value = "/getCalendarTips.do",method = RequestMethod.POST)
	public @ResponseBody
	BaseObjResponse<List<PlanTips>> getCalendarTips(@RequestBody GetCalendarTipsReq req){
		if(req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS){
			return new BaseObjResponse<List<PlanTips>>(req.isCorrectParams());
		}
		return appUserLearningPlanService.getCalendarTips(req);
	}

	/**
	 * 修改日历提醒
	 * @return
	 */
	@ApiOperation(value = "学习计划－修改日历提醒", httpMethod = "POST", notes = "学习计划－修改日历提醒", position=11)
	@RequestMapping(value = "/updateCalendarTips.do",method = RequestMethod.POST)
	public @ResponseBody
	BaseObjResponse<AppTips> updateCalendarTips(@RequestBody UpdateCalendarTipsReq req){
		if(req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS){
			return new BaseObjResponse<AppTips>(req.isCorrectParams());
		}
		return appUserLearningPlanService.updateCalendarTips(req);
	}


	/************************************页面接口*************************************/
	/**
	 * 获取英文单词详细信息
	 * @param model
     * @return
     */
	@RequestMapping(value = "/getAccaEnglishWordDesc.do",method = RequestMethod.GET)
	public String getAccaEnglishWordDesc(Model model,String id){
		AppAccaEnglishWord word = appUserLearningPlanService.getWordDesc(id);
		model.addAttribute("word",word);
		return "/api/plan/worddesc";
	}

	@ApiOperation(value = "小程序单词列表接口", httpMethod = "POST", notes = "ACCA单词列表")
	@RequestMapping(value = "/getACCAwordList.do", method = RequestMethod.POST)
	public @ResponseBody String getACCAwordList(){
		String resp = appUserLearningPlanService.getAccaWordList();
		return resp;
	}
	@ApiOperation(value = "小程序单词详情接口", httpMethod = "GET", notes = "ACCA单词详情")
	@RequestMapping(value = "/getAccaWordDetail.do",method = RequestMethod.GET)
	public  @ResponseBody String getAccaWordDetail(@RequestParam(value = "name") String name){
		System.out.println("name:"+name);
		String word = appUserLearningPlanService.getAccaWordDetail(name);
		return word;
	}

	@ApiOperation(value = "学习计划－获取财经词汇列表", httpMethod = "POST", notes = "学习计划－获取财经词汇列表", position=1)
	@RequestMapping(value = "/getAccaEnglishWordList.do")
	public @ResponseBody
	BasePageResponse<AppAccaEnglishWord> getAccaEnglishWordList(@RequestBody BaseRequest req){
		BasePageResponse<AppAccaEnglishWord> resp = appUserLearningPlanService.getAccaEnglishWordList(req);
		return resp;
	}
	
}