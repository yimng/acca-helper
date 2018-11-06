package com.thinkgem.jeesite.acca.web.feedback.web;

import com.thinkgem.jeesite.acca.web.feedback.entity.QuestionCategory;
import com.thinkgem.jeesite.acca.web.feedback.service.QuestionCategoryService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
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
@RequestMapping(value = "${adminPath}/web/questioncategory")
public class QuestionCategoryController extends BaseController {
    @Autowired
    private QuestionCategoryService service;

    @ModelAttribute
    public QuestionCategory get(@RequestParam(required = false) String id) {
        QuestionCategory entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new QuestionCategory();
        }
        return entity;
    }

    @RequiresPermissions("user:webFeedback:view")
    @RequestMapping(value = {"list", ""})
    public String list(Model model) {
        List<QuestionCategory> list = service.findAllList();
        model.addAttribute("list", list);
        return "web/question/questioncategoryList";
    }

    @RequiresPermissions("user:webFeedback:view")
    @RequestMapping(value = "form")
    public String form(QuestionCategory category, Model model) {
        model.addAttribute("category", category);
        return "web/question/questioncategoryForm";
    }

    @RequiresPermissions("user:webFeedback:edit")
    @RequestMapping(value = "save")
    public String save(QuestionCategory category, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, category)) {
            return form(category, model);
        }
        service.save(category);
        addMessage(redirectAttributes, "保存问题分类成功");
        return "redirect:" + Global.getAdminPath() + "/web/questioncategory/?repage";
    }
}
