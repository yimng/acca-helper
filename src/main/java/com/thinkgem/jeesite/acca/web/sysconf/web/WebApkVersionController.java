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

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebApkVersion;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebApkVersionService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * WebApkVersionController
 * @author Ivan
 * @version 2016-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/web/webApkVersion")
public class WebApkVersionController extends BaseController {

	@Autowired
	private WebApkVersionService webApkVersionService;
	
	@ModelAttribute
	public WebApkVersion get(@RequestParam(required=false) String id) {
		WebApkVersion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webApkVersionService.get(id);
		}
		if (entity == null){
			entity = new WebApkVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("web:webApkVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebApkVersion webApkVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebApkVersion> page = webApkVersionService.findPage(new Page<WebApkVersion>(request, response), webApkVersion); 
		model.addAttribute("page", page);
		return "web/sysconf/webApkVersionList";
	}

	@RequiresPermissions("web:webApkVersion:view")
	@RequestMapping(value = "form")
	public String form(WebApkVersion webApkVersion, Model model) {
		model.addAttribute("webApkVersion", webApkVersion);
		return "web/sysconf/webApkVersionForm";
	}

	@RequiresPermissions("web:webApkVersion:edit")
	@RequestMapping(value = "save")
	public String save(WebApkVersion webApkVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webApkVersion)){
			return form(webApkVersion, model);
		}
		webApkVersionService.save(webApkVersion);
		addMessage(redirectAttributes, "修改成功");
		return "redirect:"+Global.getAdminPath()+"/web/webApkVersion/?repage";
	}
	
	@RequiresPermissions("web:webApkVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(WebApkVersion webApkVersion, RedirectAttributes redirectAttributes) {
		webApkVersionService.delete(webApkVersion);
		addMessage(redirectAttributes, "删除WebApkVersion成功");
		return "redirect:"+Global.getAdminPath()+"/web/webApkVersion/?repage";
	}

}