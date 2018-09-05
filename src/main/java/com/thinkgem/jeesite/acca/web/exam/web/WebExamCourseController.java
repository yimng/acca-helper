/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.web;

import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamVersion;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamCourseService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 考试Controller
 * @author Fan
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/web/webExamCourse")
public class WebExamCourseController extends BaseController {

	@Autowired
	private WebExamCourseService webExamCourseService;

	@ModelAttribute
	public WebExamCourse get(@RequestParam(required=false) String id) {
		WebExamCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webExamCourseService.get(id);
		}
		if (entity == null){
			entity = new WebExamCourse();
		}
		return entity;
	}

	@RequiresPermissions("web:webExamCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebExamCourse webExamCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebExamCourse> page = webExamCourseService.findPage(new Page<WebExamCourse>(request, response), webExamCourse);
		model.addAttribute("page", page);
		return "web/exam/exam/webExamCourseList";
	}

	@RequiresPermissions("web:webExamCourse:view")
	@RequestMapping(value = "form")
	public String form(WebExamCourse webExamCourse, Model model) {
		Long examCourseId = webExamCourse.getExamCourseId();
		if (examCourseId != null){
			List<WebExamVersion> examCourseVersion = webExamCourseService.getExamCourseVersion(examCourseId);
			webExamCourse.setVersionList(examCourseVersion);
		}
		model.addAttribute("webExamCourse", webExamCourse);
		return "web/exam/exam/webExamCourseForm";
	}

	/**
	 * 修改或保存科目信息
	 * @param webExamCourse
	 * @param model
	 * @param redirectAttributes
	 * @param versions
     * @return
     */
	@RequiresPermissions("web:webExamCourse:edit")
	@RequestMapping(value = "save")
	public String save(WebExamCourse webExamCourse, Model model, RedirectAttributes redirectAttributes,String versions) {
		if (!beanValidator(model, webExamCourse)){
			return form(webExamCourse, model);
		}
		if (webExamCourse.getExamType() == 0 || webExamCourse.getExamType() == null){
			addMessage(model, "考试类型不能为空!");
			return form(webExamCourse, model);
		}
		String message = webExamCourseService.save(webExamCourse, versions);
		addMessage(redirectAttributes, message);
		return "redirect:"+Global.getAdminPath()+"/web/webExamCourse/?repage";
	}

	/**
	 * 添加考试版本信息
	 * @param versionName
	 * @return
     */
	@RequiresPermissions("web:webExamCourse:edit")
	@RequestMapping(value = "addExamVersion")
	@ResponseBody
	public Long addExamVersion(String versionName) {
		Long versionId = null;
		if (versionName != null && !"".equals(versionName)){
			versionId = webExamCourseService.addExamVersion(versionName);
		}
		return versionId;
	}

}