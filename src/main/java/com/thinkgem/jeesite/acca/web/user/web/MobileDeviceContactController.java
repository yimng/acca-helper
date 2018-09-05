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
 * AppMobileDeviceContactController
 * @author Ivan
 * @version 2016-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/user/mobileDeviceContact")
public class MobileDeviceContactController extends BaseController {

	@Autowired
	private MobileDeviceContactService mobileDeviceContactService;
	
	@Autowired
	private WebMobileDeviceService webMobileDeviceService;
	
	@ModelAttribute
	public MobileDeviceContact get(@RequestParam(required=false) String id) {
		MobileDeviceContact entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mobileDeviceContactService.get(id);
		}
		if (entity == null){
			entity = new MobileDeviceContact();
		}
		return entity;
	}
	
	@RequiresPermissions("user:mobileDeviceContact:view")
	@RequestMapping(value = {"list", ""})
	public String list(MobileDeviceContact mobileDeviceContact, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MobileDeviceContact> page = mobileDeviceContactService.findPage(new Page<MobileDeviceContact>(request, response), mobileDeviceContact);
		
		WebMobileDevice entity=new WebMobileDevice();
		entity.setDeviceId(mobileDeviceContact.getDeviceId());
		WebMobileDevice webMobileDevice =webMobileDeviceService.get(entity);
		
		model.addAttribute("webMobileDevice", webMobileDevice);
		model.addAttribute("page", page);
		return "web/user/mobileDeviceContactList";
	}
	
	@RequiresPermissions("user:mobileDeviceContact:view")
	@RequestMapping(value = {"userDeviceList"})
	public String userDeviceList(MobileDeviceContact mobileDeviceContact, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MobileDeviceContact> page = mobileDeviceContactService.findPage(new Page<MobileDeviceContact>(request, response), mobileDeviceContact);
		
		WebMobileDevice entity=new WebMobileDevice();
		entity.setDeviceId(mobileDeviceContact.getDeviceId());
		webMobileDeviceService.get(entity);
		
		model.addAttribute("webMobileDevice", entity);
		model.addAttribute("page", page);
		return "web/user/webUserDeviceList";
	}

	@RequiresPermissions("user:mobileDeviceContact:view")
	@RequestMapping(value = "form")
	public String form(MobileDeviceContact mobileDeviceContact, Model model) {
		model.addAttribute("mobileDeviceContact", mobileDeviceContact);
		return "web/user/mobileDeviceContactForm";
	}

	@RequiresPermissions("user:mobileDeviceContact:edit")
	@RequestMapping(value = "save")
	public String save(MobileDeviceContact mobileDeviceContact, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mobileDeviceContact)){
			return form(mobileDeviceContact, model);
		}
		mobileDeviceContactService.save(mobileDeviceContact);
		addMessage(redirectAttributes, "保存AppMobileDeviceContact成功");
		return "redirect:"+Global.getAdminPath()+"/user/mobileDeviceContact/?repage";
	}
	
	@RequiresPermissions("user:mobileDeviceContact:edit")
	@RequestMapping(value = "delete")
	public String delete(MobileDeviceContact mobileDeviceContact, RedirectAttributes redirectAttributes) {
		mobileDeviceContactService.delete(mobileDeviceContact);
		addMessage(redirectAttributes, "删除AppMobileDeviceContact成功");
		return "redirect:"+Global.getAdminPath()+"/user/mobileDeviceContact/?repage";
	}

}