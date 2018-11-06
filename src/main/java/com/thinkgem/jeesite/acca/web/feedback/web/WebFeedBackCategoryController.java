package com.thinkgem.jeesite.acca.web.feedback.web;

import com.thinkgem.jeesite.acca.web.feedback.entity.FbCategory;
import com.thinkgem.jeesite.acca.web.feedback.service.FbCateGoryService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.PageInfo;
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

@Controller
@RequestMapping(value = "${adminPath}/user/webFeedbackCategory")
public class WebFeedBackCategoryController extends BaseController {
    @Autowired
    private FbCateGoryService service;

    @ModelAttribute
    public FbCategory get(@RequestParam(required = false) String id) {
        FbCategory entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new FbCategory();
        }
        return entity;
    }

    @RequiresPermissions("user:webFeedback:view")
    @RequestMapping(value = {"list", ""})
    public String list(FbCategory category, Model model,
                       @RequestParam(required = false, defaultValue = "1") int pageNo,
                       @RequestParam(required = false, defaultValue = "30") int pageSize) {
        PageInfo page = service.findPage(category, pageNo, pageSize);
        model.addAttribute("page", page);
        return "web/feedback/webFeedbackCategoryList";
    }

    @RequiresPermissions("user:webFeedback:view")
    @RequestMapping(value = "form")
    public String form(FbCategory category, Model model) {
        model.addAttribute("fbcategory", category);
        return "web/feedback/webFeedbackCategoryForm";
    }

    @RequiresPermissions("user:webFeedback:edit")
    @RequestMapping(value = "save")
    public String save(FbCategory category, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, category)) {
            return form(category, model);
        }
        service.save(category);
        addMessage(redirectAttributes, "保存WebFeedback成功");
        return "redirect:" + Global.getAdminPath() + "/user/webFeedbackCategory/?repage";
    }
}
