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

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebAccaClub;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebAccaClubService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * WebAccaClub模块Controller
 * @author Ivan
 * @version 2016-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/web/webAccaClub")
public class WebAccaClubController extends BaseController {

	@Autowired
	private WebAccaClubService webAccaClubService;
	
	@ModelAttribute
	public WebAccaClub get(@RequestParam(required=false) String id) {
		WebAccaClub entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webAccaClubService.get(id);
		}
		if (entity == null){
			entity = new WebAccaClub();
		}
		return entity;
	}
	
	@RequiresPermissions("web:webAccaClub:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebAccaClub webAccaClub, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebAccaClub> page = webAccaClubService.findPage(new Page<WebAccaClub>(request, response), webAccaClub); 
		model.addAttribute("page", page);
		return "web/sysconf/webAccaClubList";
	}

	@RequiresPermissions("web:webAccaClub:view")
	@RequestMapping(value = "form")
	public String form(WebAccaClub webAccaClub, Model model) {
		model.addAttribute("webAccaClub", webAccaClub);
		return "web/sysconf/webAccaClubForm";
	}

	@RequiresPermissions("web:webAccaClub:edit")
	@RequestMapping(value = "save")
	public String save(WebAccaClub webAccaClub, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webAccaClub)){
			return form(webAccaClub, model);
		}
		webAccaClubService.save(webAccaClub);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/web/webAccaClub/?repage";
	}
	
	@RequiresPermissions("web:webAccaClub:edit")
	@RequestMapping(value = "delete")
	public String delete(WebAccaClub webAccaClub, RedirectAttributes redirectAttributes) {
		webAccaClubService.delete(webAccaClub);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/web/webAccaClub/?repage";
	}

}