/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.feedback.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.acca.web.feedback.entity.WebFeedback;
import com.thinkgem.jeesite.acca.web.feedback.service.WebFeedbackService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * WebFeedbackController
 * @author Ivan
 * @version 2016-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/user/webFeedback")
public class WebFeedbackController extends BaseController {

	@Autowired
	private WebFeedbackService webFeedbackService;
	
	@ModelAttribute
	public WebFeedback get(@RequestParam(required=false) String id) {
		WebFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webFeedbackService.get(id);
		}
		if (entity == null){
			entity = new WebFeedback();
		}
		return entity;
	}
	
	@RequiresPermissions("user:webFeedback:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebFeedback webFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebFeedback> page = webFeedbackService.findPage(new Page<WebFeedback>(request, response), webFeedback); 
		model.addAttribute("page", page);
		return "web/feedback/webFeedbackList";
	}

	@RequiresPermissions("user:webFeedback:view")
	@RequestMapping(value = "form")
	public String form(WebFeedback webFeedback, Model model) {
		model.addAttribute("webFeedback", webFeedback);
		return "web/feedback/webFeedbackForm";
	}

	@RequiresPermissions("user:webFeedback:edit")
	@RequestMapping(value = "save")
	public String save(WebFeedback webFeedback, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webFeedback)){
			return form(webFeedback, model);
		}
		webFeedbackService.save(webFeedback);
		addMessage(redirectAttributes, "保存WebFeedback成功");
		return "redirect:"+Global.getAdminPath()+"/user/webFeedback/?repage";
	}
	
	@RequiresPermissions("user:webFeedback:edit")
	@RequestMapping(value = "delete")
	public String delete(WebFeedback webFeedback, RedirectAttributes redirectAttributes) {
		webFeedbackService.delete(webFeedback);
		addMessage(redirectAttributes, "删除WebFeedback成功");
		return "redirect:"+Global.getAdminPath()+"/user/webFeedback/?repage";
	}

}