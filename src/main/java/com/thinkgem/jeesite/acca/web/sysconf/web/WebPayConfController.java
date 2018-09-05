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

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebPayConf;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebPayConfService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 收款账户管理Controller
 * @author Fan
 * @version 2016-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/web/webPayConf")
public class WebPayConfController extends BaseController {

	@Autowired
	private WebPayConfService webPayConfService;
	
	@ModelAttribute
	public WebPayConf get(@RequestParam(required=false) String id) {
		WebPayConf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webPayConfService.get(id);
		}
		if (entity == null){
			entity = new WebPayConf();
		}
		return entity;
	}
	
	@RequiresPermissions("web:webPayConf:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebPayConf webPayConf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebPayConf> page = webPayConfService.findPage(new Page<WebPayConf>(request, response), webPayConf); 
		model.addAttribute("page", page);
		return "web/sysconf/webPayConfList";
	}

	@RequiresPermissions("web:webPayConf:view")
	@RequestMapping(value = "form")
	public String form(WebPayConf webPayConf, Model model) {
		model.addAttribute("webPayConf", webPayConf);
		return "web/sysconf/webPayConfForm";
	}

	@RequiresPermissions("web:webPayConf:edit")
	@RequestMapping(value = "save")
	public String save(WebPayConf webPayConf, Model model, RedirectAttributes redirectAttributes) {
		logger.info("webPayConf:"+webPayConf);
		if (!beanValidator(model, webPayConf)){
			return form(webPayConf, model);
		}
		logger.info("webPayConf:"+webPayConf);
		webPayConfService.save(webPayConf);
		addMessage(redirectAttributes, "保存收款账户成功");
		return "redirect:"+Global.getAdminPath()+"/web/webPayConf/?repage";
	}
	
	@RequiresPermissions("web:webPayConf:edit")
	@RequestMapping(value = "delete")
	public String delete(WebPayConf webPayConf, RedirectAttributes redirectAttributes) {
		webPayConfService.delete(webPayConf);
		addMessage(redirectAttributes, "删除收款账户成功");
		return "redirect:"+Global.getAdminPath()+"/web/webPayConf/?repage";
	}

}