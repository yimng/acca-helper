package com.thinkgem.jeesite.acca.api.user.web;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExam;
import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.model.response.article.AppArticleCollectDto;
import com.thinkgem.jeesite.acca.api.user.service.AppQuestionService;
import com.thinkgem.jeesite.acca.web.feedback.entity.ExamCenterQuestion;
import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.acca.web.feedback.service.ExamCenterQuestionService;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Api(value = "${apiPath}/question", description = "问题模块")
@Controller
@RequestMapping(value = "${apiPath}/question")
public class AppQuestionController extends BaseController {
    @Autowired
    private AppQuestionService appQuestionService;

    @Autowired
    private ExamCenterQuestionService examCenterQuestionService;

    @ApiOperation(value = "取得问题详细信息", httpMethod = "POST", notes = "取得问题详细信息")
    @RequestMapping(value = "getQuestionDetail.do", method = RequestMethod.POST)
    public @ResponseBody
    BaseObjResponse<Question> questionDetail(@RequestBody GetQuestionDetailReq req) {
        return appQuestionService.getDetail(req.getQuestionId());
    }

    @ApiOperation(value = "根据问题分类获得问题列表", httpMethod = "POST", notes = "根据问题分类获得问题列表")
    @RequestMapping(value = "getQuestionsByCategory.do", method = RequestMethod.POST)
    public @ResponseBody
    BasePageResponse<Question> getQuestionsByCategory(@RequestBody GetCategoryQuestion req) {
        return appQuestionService.getQuestionsByCategory(req.getPage().getStartIndex(), req.getPage().getPageSize(),req.getCatergoryId());
    }

    @ApiOperation(value = "获取热点问题列表", httpMethod = "POST", notes = "获取热点问题列表")
    @RequestMapping(value = "getHotQuestions.do", method = RequestMethod.POST)
    public @ResponseBody
    BasePageResponse<Question> getHotQuestions(@RequestBody BasePageRequest req) {
        return appQuestionService.getHotQuestions(req.getPage().getStartIndex(), req.getPage().getPageSize(),true);
    }

    @ApiOperation(value = "搜索问题列表", httpMethod = "POST", notes = "搜索问题列表")
    @RequestMapping(value = "searchQuestions.do", method = RequestMethod.POST)
    public @ResponseBody
    BasePageResponse<Question> searchQuestions(@RequestBody SearchQuestionReq req) {
        return appQuestionService.searchQuestions(req.getPage().getStartIndex(), req.getPage().getPageSize(),req.getTitle());
    }

    @ApiOperation(value = "对问题进行评价", httpMethod = "POST", notes = "对问题进行评价")
    @RequestMapping(value = "praiseQuestion.do", method = RequestMethod.POST)
    public @ResponseBody
    BaseObjResponse<Question> praiseQuestion(@RequestBody PraiseQuestionReq req) {
        return appQuestionService.praiseQuestion(req.getQuestionId(), req.getPraise());
    }

    @ApiOperation(value = "电话咨询", httpMethod = "POST", notes = "电话咨询")
    @RequestMapping(value = "getExamCenterList.do", method = RequestMethod.POST)
    public @ResponseBody
    BasePageResponse getExamCenter(@RequestBody ExamCenterReq req) {
        int respCode = req.isCorrectParams();
        if(respCode!=RespConstants.GLOBAL_SUCCESS){
            return new BasePageResponse<AppExam>(respCode);
        }
        return examCenterQuestionService.getExamCenterList(req, req.getPage().getStartIndex(), req.getPage().getPageSize());
    }

    @ApiOperation(value = "机考中心问题提问", httpMethod = "POST", notes = "机考中心问题提问")
    @RequestMapping(value = "saveacquestions.do", method = RequestMethod.POST)
    public @ResponseBody
    BaseResponse saveacquestions(@RequestBody EcQuestionReq req) {
        int resp = req.isCorrectParams();
        if (resp!=RespConstants.GLOBAL_SUCCESS) {
            return new BaseResponse(resp);
        }
        ExamCenterQuestion question = new ExamCenterQuestion();
        question.setAccaUserId(req.getAppUserId());
        question.setAccaUserNickname(req.getAppUser().getNickname());
        question.setAccaUserPhone(req.getAppUser().getPhone());
        question.setCreateTime(new Date());
        question.setDelFlag("0");
        question.setExamPlaceId(req.getExamPlaceId());
        question.setExamPlaceName(req.getExamPlaceName());
        question.setTitile(req.getTitile());
        examCenterQuestionService.save(question);
        return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
    }
}
