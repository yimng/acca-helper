package com.thinkgem.jeesite.acca.api.user.web;

import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.user.service.AppQuestionService;
import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "${apiPath}/question", description = "问题模块")
@Controller
@RequestMapping(value = "${apiPath}/question")
public class AppQuestionController extends BaseController {
    @Autowired
    private AppQuestionService appQuestionService;

    @ApiOperation(value = "取得问题详细信息", httpMethod = "GET", notes = "取得问题详细信息")
    @RequestMapping(value = "getQuestionDetail.do", method = RequestMethod.GET)
    public @ResponseBody
    BaseObjResponse<Question> questionDetail(@RequestBody GetQuestionDetailReq req) {
        return appQuestionService.getDetail(req.getQuestionId());
    }

    @ApiOperation(value = "根据问题分类获得问题列表", httpMethod = "GET", notes = "根据问题分类获得问题列表")
    @RequestMapping(value = "getQuestionsByCategory.do", method = RequestMethod.GET)
    public @ResponseBody
    BasePageResponse<Question> getQuestionsByCategory(@RequestBody GetCategoryQuestion req) {
        return appQuestionService.getQuestionsByCategory(req.getPage().getStartIndex(), req.getPage().getPageSize(),req.getCatergoryId());
    }

    @ApiOperation(value = "获取热点问题列表", httpMethod = "GET", notes = "获取热点问题列表")
    @RequestMapping(value = "getHotQuestions.do", method = RequestMethod.GET)
    public @ResponseBody
    BasePageResponse<Question> getHotQuestions(@RequestBody GetHotQuestionReq req) {
        return appQuestionService.getHotQuestions(req.getPage().getStartIndex(), req.getPage().getPageSize(),req.isHot());
    }

    @ApiOperation(value = "搜索问题列表", httpMethod = "GET", notes = "搜索问题列表")
    @RequestMapping(value = "searchQuestions.do", method = RequestMethod.GET)
    public @ResponseBody
    BasePageResponse<Question> searchQuestions(@RequestBody SearchQuestionReq req) {
        return appQuestionService.searchQuestions(req.getPage().getStartIndex(), req.getPage().getPageSize(),req.getTitle());
    }

    @ApiOperation(value = "对问题进行评价", httpMethod = "Post", notes = "对问题进行评价")
    @RequestMapping(value = "praiseQuestion.do", method = RequestMethod.POST)
    public @ResponseBody
    BaseObjResponse<Question> praiseQuestion(@RequestBody PraiseQuestionReq req) {
        return appQuestionService.praiseQuestion(req.getQuestionId(), req.getPraise());
    }
}
