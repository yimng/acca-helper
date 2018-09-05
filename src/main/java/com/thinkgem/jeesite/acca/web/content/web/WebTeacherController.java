/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

import com.thinkgem.jeesite.acca.web.content.entity.WebTeacher;
import com.thinkgem.jeesite.acca.web.content.service.WebTeacherService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
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

/**
 * 名师指导Controller
 * @author Young
 * @version 2016-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/webTeacher")
public class WebTeacherController extends BaseController {

	@Autowired
	private WebTeacherService webTeacherService;
	
	@ModelAttribute
	public WebTeacher get(@RequestParam(required=false) String id) {
		WebTeacher entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webTeacherService.get(id);
		}
		if (entity == null){
			entity = new WebTeacher();
		}
		return entity;
	}
	
	@RequiresPermissions("teacher:webTeacher:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebTeacher webTeacher, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebTeacher> page = webTeacherService.findPage(new Page<WebTeacher>(request, response), webTeacher); 
		model.addAttribute("page", page);
		return "web/content/teacher/webTeacherList";
	}

	@RequiresPermissions("teacher:webTeacher:view")
	@RequestMapping(value = "form")
	public String form(WebTeacher webTeacher, Model model) {
		if (webTeacher.getTeacherId() != null) {
            webTeacher = webTeacherService.get(webTeacher);
        }
		model.addAttribute("webTeacher", webTeacher);
		return "web/content/teacher/webTeacherForm";
	}

	/**
	 * 保存或更新名师信息
	 * @param webTeacher
	 * @param model
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("teacher:webTeacher:edit")
	@RequestMapping(value = "save")
	public String save(WebTeacher webTeacher, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webTeacher)){
			return form(webTeacher, model);
		}
		String message = webTeacherService.saveTeacher(webTeacher);
		addMessage(redirectAttributes, message);
		return "redirect:"+Global.getAdminPath()+"/teacher/webTeacher/?repage";
	}

	/**
	 * 删除名师信息
	 * @param webTeacher
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("teacher:webTeacher:edit")
	@RequestMapping(value = "delete")
	public String delete(WebTeacher webTeacher, RedirectAttributes redirectAttributes) {
		webTeacherService.delete(webTeacher);
		addMessage(redirectAttributes, "删除名师成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/webTeacher/?repage";
	}

}