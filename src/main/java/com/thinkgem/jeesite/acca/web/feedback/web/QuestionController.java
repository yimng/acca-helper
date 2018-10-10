package com.thinkgem.jeesite.acca.web.feedback.web;

import com.thinkgem.jeesite.acca.web.feedback.dao.QuestionCategoryMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.acca.web.feedback.entity.QuestionCategory;
import com.thinkgem.jeesite.acca.web.feedback.service.QuestionService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/web/question")
public class QuestionController extends BaseController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryMapper questionCategoryMapper;

    @ModelAttribute
    public Question get(@RequestParam(required = false) Long id) {
        Question entity = null;
        if (id != null) {
            entity = questionService.get(id);
        }
        if (entity == null) {
            entity = new Question();
        }
        return entity;
    }

    @RequiresPermissions("web:question:edit")
    @RequestMapping(value = {"form"})
    public String form(Question question, Model model) {
        List<QuestionCategory> questionCategories = questionCategoryMapper.selectAll();
        model.addAttribute("category", questionCategories);
        model.addAttribute("question", question);
        return "web/question/questionForm";
    }

    @RequiresPermissions("web:question:view")
    @RequestMapping(value = {"list", ""})
    public String list(Question question, Model model,
                       @RequestParam(required = false, defaultValue = "1") int pageNo,
                       @RequestParam(required = false, defaultValue = "30") int pageSize) {
        PageInfo page = questionService.findPage(question, pageNo, pageSize);
        model.addAttribute("page", page);
        List<QuestionCategory> questionCategories = questionCategoryMapper.selectAll();
        model.addAttribute("category", questionCategories);
        return "web/question/questionList";
    }

    @RequiresPermissions("web:question:edit")
    @RequestMapping(value = "save")
    public String save(Question question, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, question)) {
            return form(question, model);
        }
        User user = UserUtils.getUser();
        questionService.save(question);
        addMessage(redirectAttributes, "保存Question成功");
        return "redirect:" + Global.getAdminPath() + "/web/question/?repage";
    }
}
