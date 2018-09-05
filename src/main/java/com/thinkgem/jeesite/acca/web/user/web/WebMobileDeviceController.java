/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.web;

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

import com.thinkgem.jeesite.acca.web.user.entity.MobileDeviceContact;
import com.thinkgem.jeesite.acca.web.user.entity.WebMobileDevice;
import com.thinkgem.jeesite.acca.web.user.service.MobileDeviceContactService;
import com.thinkgem.jeesite.acca.web.user.service.WebMobileDeviceService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * WebMobileDeviceController
 * @author Ivan
 * @version 2016-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/user/webMobileDevice")
public class WebMobileDeviceController extends BaseController {

	@Autowired
	private WebMobileDeviceService webMobileDeviceService;
	
	
	
	@ModelAttribute
	public WebMobileDevice get(@RequestParam(required=false) String id) {
		WebMobileDevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webMobileDeviceService.get(id);
		}
		if (entity == null){
			entity = new WebMobileDevice();
		}
		return entity;
	}
	
	@RequiresPermissions("user:webMobileDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebMobileDevice webMobileDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebMobileDevice> page = webMobileDeviceService.findPage(new Page<WebMobileDevice>(request, response), webMobileDevice); 
		model.addAttribute("page", page);
		return "web/user/webMobileDeviceList";
	}
	
	/*
	@RequiresPermissions("user:webMobileDevice:view")
	@RequestMapping(value = "detail")
	public String detail(WebMobileDevice webMobileDevice, Model model) {
		MobileDeviceContact contact = new MobileDeviceContact();
		contact.setDeviceId(webMobileDevice.getDeviceId());
		mobileDeviceContactService.findPage(contact);
		model.addAttribute("webMobileDevice", webMobileDevice);
		return "web/user/webMobileDeviceForm";
	}*/

	@RequiresPermissions("user:webMobileDevice:view")
	@RequestMapping(value = "form")
	public String form(WebMobileDevice webMobileDevice, Model model) {
		model.addAttribute("webMobileDevice", webMobileDevice);
		return "web/user/webMobileDeviceForm";
	}

	@RequiresPermissions("user:webMobileDevice:edit")
	@RequestMapping(value = "save")
	public String save(WebMobileDevice webMobileDevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webMobileDevice)){
			return form(webMobileDevice, model);
		}
		webMobileDeviceService.save(webMobileDevice);
		addMessage(redirectAttributes, "保存WebMobileDevice成功");
		return "redirect:"+Global.getAdminPath()+"/user/webMobileDevice/?repage";
	}
	
	@RequiresPermissions("user:webMobileDevice:edit")
	@RequestMapping(value = "delete")
	public String delete(WebMobileDevice webMobileDevice, RedirectAttributes redirectAttributes) {
		webMobileDeviceService.delete(webMobileDevice);
		addMessage(redirectAttributes, "删除WebMobileDevice成功");
		return "redirect:"+Global.getAdminPath()+"/user/webMobileDevice/?repage";
	}

}