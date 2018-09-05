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

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfRegisterFee;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebConfRegisterFeeService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 注册用户费用管理Controller
 * @author Fan
 * @version 2016-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/web/webConfRegisterFee")
public class WebConfRegisterFeeController extends BaseController {

	@Autowired
	private WebConfRegisterFeeService webConfRegisterFeeService;
	
	@ModelAttribute
	public WebConfRegisterFee get(@RequestParam(required=false) String id) {
		WebConfRegisterFee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webConfRegisterFeeService.get(id);
		}
		if (entity == null){
			entity = new WebConfRegisterFee();
		}
		return entity;
	}
	
	@RequiresPermissions("web:webConfRegisterFee:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebConfRegisterFee webConfRegisterFee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebConfRegisterFee> page = webConfRegisterFeeService.findPage(new Page<WebConfRegisterFee>(request, response), webConfRegisterFee); 
		model.addAttribute("page", page);
		return "web/sysconf/webConfRegisterFeeList";
	}

	@RequiresPermissions("web:webConfRegisterFee:view")
	@RequestMapping(value = "form")
	public String form(WebConfRegisterFee webConfRegisterFee, Model model) {
		model.addAttribute("webConfRegisterFee", webConfRegisterFee);
		return "web/sysconf/webConfRegisterFeeForm";
	}

	@RequiresPermissions("web:webConfRegisterFee:edit")
	@RequestMapping(value = "save")
	public String save(WebConfRegisterFee webConfRegisterFee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webConfRegisterFee)){
			return form(webConfRegisterFee, model);
		}
		webConfRegisterFeeService.save(webConfRegisterFee);
		addMessage(redirectAttributes, "修改注册费用成功");
		return "redirect:"+Global.getAdminPath()+"/web/webConfRegisterFee/?repage";
	}
	
	@RequiresPermissions("web:webConfRegisterFee:edit")
	@RequestMapping(value = "delete")
	public String delete(WebConfRegisterFee webConfRegisterFee, RedirectAttributes redirectAttributes) {
		webConfRegisterFeeService.delete(webConfRegisterFee);
		addMessage(redirectAttributes, "删除注册用户费用管理成功");
		return "redirect:"+Global.getAdminPath()+"/web/webConfRegisterFee/?repage";
	}

}