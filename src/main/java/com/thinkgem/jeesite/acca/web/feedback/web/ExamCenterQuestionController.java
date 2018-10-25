package com.thinkgem.jeesite.acca.web.feedback.web;

import com.thinkgem.jeesite.acca.web.feedback.entity.ExamCenterAnswer;
import com.thinkgem.jeesite.acca.web.feedback.entity.ExamCenterQuestion;
import com.thinkgem.jeesite.acca.web.feedback.service.ExamCenterAnswerService;
import com.thinkgem.jeesite.acca.web.feedback.service.ExamCenterQuestionService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping(value = "${adminPath}/web/ecquestion")
public class ExamCenterQuestionController extends BaseController {
    @Autowired
    private ExamCenterQuestionService questionService;
    @Autowired
    private ExamCenterAnswerService answerService;

    @ModelAttribute
    public ExamCenterQuestion get(@RequestParam(required = false) Long id) {
        ExamCenterQuestion entity = null;
        if (id != null) {
            entity = questionService.get(id);
        }
        if (entity == null) {
            entity = new ExamCenterQuestion();
        }
        return entity;
    }

    @RequiresPermissions("web:ecquestion:view")
    @RequestMapping(value = {"form"})
    public String form(ExamCenterQuestion question, Model model) {
        return "web/question/ecquestionForm";
    }

    @RequiresPermissions("web:ecquestion:view")
    @RequestMapping(value = {"list", ""})
    public String list(ExamCenterQuestion question, Model model,
                       @RequestParam(required = false, defaultValue = "1") int pageNo,
                       @RequestParam(required = false, defaultValue = "3") int pageSize) {
        PageInfo page = questionService.findPage(question, pageNo, pageSize);
        model.addAttribute("page", page);
        return "web/question/ecquestionList";
    }

    @RequiresPermissions("web:ecquestion:view")
    @RequestMapping(value = {"view"})
    public String viewAnswer(Long id, Model model,
                             @RequestParam(required = false, defaultValue = "1") int pageNo,
                             @RequestParam(required = false, defaultValue = "3") int pageSize) {
        ExamCenterAnswer answer = new ExamCenterAnswer();
        answer.setQuestionId(id);
        PageInfo<ExamCenterAnswer> page = answerService.findPage(answer, pageNo, pageSize);
        model.addAttribute("page", page);
        return "web/question/answerList";
    }

    @RequiresPermissions("web:ecquestion:view")
    @RequestMapping(value = {"saveanswer"})
    public String saveAnswer(Model model, ExamCenterAnswer answer) {
        answer.setDelFlag("0");
        answer.setCreateTime(new Date());
        User user = UserUtils.getUser();
        answer.setSysUserId(user.getId());
        answer.setSysUserName(user.getName());
        answer.setSysUserPhone(user.getPhone());
        answerService.save(answer);
        return "redirect:" + Global.getAdminPath() + "/web/ecquestion/?repage";
    }
    @RequiresPermissions("web:ecquestion:view")
    @RequestMapping(value = {"delete"})
    public String deleteQuestion(Model model, Long id) {
        questionService.delete(id);
        return "redirect:" + Global.getAdminPath() + "/web/ecquestion/?repage";
    }
    @RequiresPermissions("web:ecquestion:view")
    @RequestMapping(value = {"deleteanswer"})
    public String deleteAnswer(Model model, Long id) {
        answerService.delete(id);
        return "redirect:" + Global.getAdminPath() + "/web/ecquestion/?repage";
    }
}

