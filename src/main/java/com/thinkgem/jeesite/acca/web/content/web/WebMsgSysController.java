/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

import com.thinkgem.jeesite.acca.web.content.entity.WebArticle;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleCategory;
import com.thinkgem.jeesite.acca.web.content.entity.WebMsgSys;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleCategoryService;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleService;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统消息Controller
 * @author Young
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/sysmsg/webMsgSys")
public class WebMsgSysController extends BaseController {

	@Autowired
	private WebMsgSysService webMsgSysService;
	@Autowired
	private WebArticleCategoryService articleCategoryService;
	@Autowired
	private WebExamCourseService examCourseService;
	@Autowired
	private WebArticleService articleService;

	@ModelAttribute
	public WebMsgSys get(@RequestParam(required=false) String id) {
		WebMsgSys entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webMsgSysService.get(id);
		}
		if (entity == null){
			entity = new WebMsgSys();
		}
		return entity;
	}
	
	@RequiresPermissions("sysmsg:webMsgSys:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebMsgSys webMsgSys, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebMsgSys> page = webMsgSysService.findPage(new Page<WebMsgSys>(request, response), webMsgSys); 
		model.addAttribute("page", page);
		return "web/content/msg/webMsgSysList";
	}

	@RequiresPermissions("sysmsg:webMsgSys:view")
	@RequestMapping(value = "form")
	public String form(WebMsgSys webMsgSys, Model model) {
		//获取科目列表
		List<WebExamCourse> examCourses = examCourseService.findList(new WebExamCourse());
		//获取分类列表
		List<WebArticleCategory> categoryList = articleCategoryService.findList(new WebArticleCategory());
		if (webMsgSys.getMsgId() != null){
			webMsgSys = webMsgSysService.get(webMsgSys);
			WebArticle webArticle = new WebArticle();
			webArticle.setArticleId(webMsgSys.getArticleId());
			webArticle = articleService.get(webArticle);
			if (webArticle != null){
				model.addAttribute("articleCategoryId", webArticle.getArticleCategoryId());
				model.addAttribute("articleCategoryName",webArticle.getCategoryName());
			}
		}
		model.addAttribute("examCourses",examCourses);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("webMsgSys", webMsgSys);
		if (webMsgSys.getMsgId() != null){
			return "web/content/msg/msgDescription";
		}
		return "web/content/msg/webMsgSysForm";
	}

	@RequiresPermissions("sysmsg:webMsgSys:edit")
	@RequestMapping(value = "save")
	public String save(WebMsgSys webMsgSys, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webMsgSys)){
			return form(webMsgSys, model);
		}
		//如果为待考试用户,判断是否选择考试科目
		if (webMsgSys.getPushPeople() == 1){
			if (StringUtils.isEmpty(webMsgSys.getExamCourse())){
				addMessage(model, "待考试科目不能为空!");
				return form(webMsgSys, model);
			}
		}
		//推送状态 0 已创建，1已发送，2发送失败不能为空
		webMsgSys.setPushStatus(0);
		//推送类型: 0 立即发送 1 定时发送
		webMsgSys.setPushType(0);
		String message = webMsgSysService.saveOrUpdate(webMsgSys);
		addMessage(redirectAttributes, message);
		return "redirect:"+Global.getAdminPath()+"/sysmsg/webMsgSys/?repage";
	}
	
	@RequiresPermissions("sysmsg:webMsgSys:edit")
	@RequestMapping(value = "delete")
	public String delete(WebMsgSys webMsgSys, RedirectAttributes redirectAttributes) {
		webMsgSysService.delete(webMsgSys);
		addMessage(redirectAttributes, "删除系统消息成功");
		return "redirect:"+Global.getAdminPath()+"/sysmsg/webMsgSys/?repage";
	}

	@RequiresPermissions("sysmsg:webMsgSys:edit")
	@RequestMapping(value = "push")
	public String push(RedirectAttributes redirectAttributes) {
		logger.error("开始给以前用户推送!");
		webMsgSysService.pushAll();
		logger.error("推送给以前的用户完成!");
		addMessage(redirectAttributes, "推送给以前的用户成功");
		return "redirect:"+Global.getAdminPath()+"/sysmsg/webMsgSys/?repage";
	}

}