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
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfHelp;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebConfHelpService;

/**
 * WebConfHelpController
 * @author Ivan
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/sysconf/webConfHelp")
public class WebConfHelpController extends BaseController {

	@Autowired
	private WebConfHelpService webConfHelpService;
	
	@ModelAttribute
	public WebConfHelp get(@RequestParam(required=false) String id) {
		WebConfHelp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webConfHelpService.get(id);
		}
		if (entity == null){
			entity = new WebConfHelp();
		}
		return entity;
	}
	
	@RequiresPermissions("sysconf:webConfHelp:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebConfHelp webConfHelp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebConfHelp> page = webConfHelpService.findPage(new Page<WebConfHelp>(request, response), webConfHelp); 
		model.addAttribute("page", page);
		return "web/sysconf/webConfHelpList";
	}

	@RequiresPermissions("sysconf:webConfHelp:view")
	@RequestMapping(value = "form")
	public String form(WebConfHelp webConfHelp, Model model) {
		model.addAttribute("webConfHelp", webConfHelp);
		return "web/sysconf/webConfHelpForm";
	}

	@RequiresPermissions("sysconf:webConfHelp:edit")
	@RequestMapping(value = "save")
	public String save(WebConfHelp webConfHelp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webConfHelp)){
			return form(webConfHelp, model);
		}
		
		webConfHelp.setHtmlContent(Encodes.unescapeHtml(webConfHelp.getHtmlContent()));
		webConfHelpService.save(webConfHelp);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/sysconf/webConfHelp/?repage";
	}
	
	@RequiresPermissions("sysconf:webConfHelp:edit")
	@RequestMapping(value = "delete")
	public String delete(WebConfHelp webConfHelp, RedirectAttributes redirectAttributes) {
		webConfHelpService.delete(webConfHelp);
		addMessage(redirectAttributes, "删除WebConfHelp成功");
		return "redirect:"+Global.getAdminPath()+"/sysconf/webConfHelp/?repage";
	}

}