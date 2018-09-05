/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.web;

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityPayRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityUserRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebExamOpenCity;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebPayConf;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebExamOpenCityService;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebPayConfService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 城市管理Controller
 * @author Fan
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/web/webExamOpenCity")
public class WebExamOpenCityController extends BaseController {

	@Autowired
	private WebExamOpenCityService webExamOpenCityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private WebPayConfService payConfService;
	
	@ModelAttribute
	public WebExamOpenCity get(@RequestParam(required=false) String id) {
		logger.info("id:"+id);
		WebExamOpenCity entity = null;
		WebExamOpenCity entity1 = new WebExamOpenCity();
		
		if (StringUtils.isNotBlank(id)){
			entity1.setId(id);
			entity = webExamOpenCityService.get(entity1);
		}
		if (entity == null){
			entity = new WebExamOpenCity();
		}
		return entity;
	}
	
	@RequiresPermissions("web:webExamOpenCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebExamOpenCity webExamOpenCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebExamOpenCity> page = webExamOpenCityService.findPage(new Page<WebExamOpenCity>(request, response), webExamOpenCity); 
		model.addAttribute("page", page);
		return "web/sysconf/webExamOpenCityList";
	}

	@RequiresPermissions("web:webExamOpenCity:view")
	@RequestMapping(value = "form")
	public String form(WebExamOpenCity webExamOpenCity, Model model) {
		//获取首款帐户列表
		List<WebPayConf> payConfList = payConfService.findList(new WebPayConf());
		//获取系统用户列表
		List<User> userList = systemService.findUser(new User());
		//获取关联帐户信息列表
		String id = webExamOpenCity.getId();
		if (StringUtils.isNotEmpty(id)){
			WebExamOpenCity city = new WebExamOpenCity();
			city.setCityId(webExamOpenCity.getCityId());
			List<WebCityPayRelation> relationList = webExamOpenCityService.findPayRelationList(city);
			model.addAttribute("relationList",relationList);
			List<WebCityUserRelation> userRelationList  = webExamOpenCityService.findUserRelationList(city);
			model.addAttribute("userRelationList",userRelationList);
		}
		model.addAttribute("payConfList",payConfList);
		model.addAttribute("userList",userList);
		model.addAttribute("webExamOpenCity", webExamOpenCity);
		model.addAttribute("pList", JsonMapper.toJsonString(webExamOpenCityService.selectProvinces()));
		model.addAttribute("cList", JsonMapper.toJsonString(webExamOpenCityService.selectCitys2()));
		return "web/sysconf/webExamOpenCityForm";
	}

	@RequiresPermissions("web:webExamOpenCity:edit")
	@RequestMapping(value = "save")
	public String save(WebExamOpenCity webExamOpenCity, Model model, RedirectAttributes redirectAttributes,String accountIds,String userIds) {
		if (!beanValidator(model, webExamOpenCity)){
			return form(webExamOpenCity, model);
		}
		if (StringUtils.isEmpty(accountIds)){
			addMessage(model, "账户名称必填!");
			return form(webExamOpenCity, model);
		}
		accountIds = accountIds.substring(1,accountIds.length());
		logger.info("webExamOpenCity:"+webExamOpenCity.getId());
		if (webExamOpenCity.getId() == null || "".equals(webExamOpenCity.getId())) {
			
			WebExamOpenCity exam=webExamOpenCityService.get(webExamOpenCity);
			if (exam != null) {
				addMessage(model, "城市已存在");
				return form(webExamOpenCity, model);
			}
		}
		webExamOpenCityService.saveOrUpdate(webExamOpenCity,accountIds,userIds);
		addMessage(redirectAttributes, "保存城市成功");
		return "redirect:"+Global.getAdminPath()+"/web/webExamOpenCity/?repage";
	}
	
	@RequiresPermissions("web:webExamOpenCity:edit")
	@RequestMapping(value = "delete")
	public String delete(WebExamOpenCity webExamOpenCity, RedirectAttributes redirectAttributes) {
		webExamOpenCityService.delete(webExamOpenCity);
		addMessage(redirectAttributes, "删除城市成功");
		return "redirect:"+Global.getAdminPath()+"/web/webExamOpenCity/?repage";
	}

}