/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfExamTips;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebConfExamTipsService;

/**
 * WebConfExamTipsController
 * @author Ivan
 * @version 2016-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/sysconf/webConfExamTips")
public class WebConfExamTipsController extends BaseController {

	@Autowired
	private WebConfExamTipsService webConfExamTipsService;
	
	@ModelAttribute
	public WebConfExamTips get(@RequestParam(required=false) String id) {
		WebConfExamTips entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webConfExamTipsService.get(id);
		}
		if (entity == null){
			entity = new WebConfExamTips();
		}
		return entity;
	}
	
	@RequiresPermissions("sysconf:webConfExamTips:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebConfExamTips webConfExamTips, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebConfExamTips> page = webConfExamTipsService.findPage(new Page<WebConfExamTips>(request, response), webConfExamTips); 
		model.addAttribute("page", page);
		return "web/sysconf/webConfExamTipsList";
	}

	@RequiresPermissions("sysconf:webConfExamTips:view")
	@RequestMapping(value = "form")
	public String form(WebConfExamTips webConfExamTips, Model model) {
		model.addAttribute("webConfExamTips", webConfExamTips);
		return "web/sysconf/webConfExamTipsForm";
	}

	@RequiresPermissions("sysconf:webConfExamTips:edit")
	@RequestMapping(value = "save")
	public String save(WebConfExamTips webConfExamTips, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webConfExamTips)){
			return form(webConfExamTips, model);
		}
		webConfExamTipsService.save(webConfExamTips);
		addMessage(redirectAttributes, "修改成功");
		return "redirect:"+Global.getAdminPath()+"/sysconf/webConfExamTips/?repage";
	}
	
	@RequiresPermissions("sysconf:webConfExamTips:edit")
	@RequestMapping(value = "delete")
	public String delete(WebConfExamTips webConfExamTips, RedirectAttributes redirectAttributes) {
		webConfExamTipsService.delete(webConfExamTips);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/sysconf/webConfExamTips/?repage";
	}

}