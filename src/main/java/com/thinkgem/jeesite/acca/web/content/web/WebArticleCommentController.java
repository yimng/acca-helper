/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleComment;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleCommentService;

/**
 * WebArticleCommentController
 * @author Ivan
 * @version 2016-09-10
 */
@Controller
@RequestMapping(value = "${adminPath}/content/webArticleComment")
public class WebArticleCommentController extends BaseController {

	@Autowired
	private WebArticleCommentService webArticleCommentService;
	
	@ModelAttribute
	public WebArticleComment get(@RequestParam(required=false) String id) {
		WebArticleComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webArticleCommentService.get(id);
		}
		if (entity == null){
			entity = new WebArticleComment();
		}
		return entity;
	}
	
	//@RequiresPermissions("content:webArticleComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebArticleComment webArticleComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebArticleComment> page = webArticleCommentService.findPage(new Page<WebArticleComment>(request, response), webArticleComment); 
		model.addAttribute("page", page);
		logger.info("webArticleComment type:{}",webArticleComment.getType());
		return "web/content/article/webArticleCommentList";
	}

/*	@RequiresPermissions("content:webArticleComment:view")
	@RequestMapping(value = "form")
	public String form(WebArticleComment webArticleComment, Model model) {
		model.addAttribute("webArticleComment", webArticleComment);
		return "web/content/webArticleCommentForm";
	}

	@RequiresPermissions("content:webArticleComment:edit")
	@RequestMapping(value = "save")
	public String save(WebArticleComment webArticleComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webArticleComment)){
			return form(webArticleComment, model);
		}
		webArticleCommentService.save(webArticleComment);
		addMessage(redirectAttributes, "保存WebArticleComment成功");
		return "redirect:"+Global.getAdminPath()+"/content/webArticleComment/?repage";
	}*/
	
	//@RequiresPermissions("content:webArticleComment:edit")
	@RequestMapping(value = "delete")
	public String delete(WebArticleComment webArticleComment, RedirectAttributes redirectAttributes) {
		
		webArticleComment = webArticleCommentService.get(webArticleComment);
		
		Long articleId = webArticleComment.getArticleId();
		Integer type = webArticleComment.getType();
		logger.info("webArticleComment:"+webArticleComment);
		webArticleCommentService.delete(webArticleComment);
		logger.info("webArticleComment:"+webArticleComment);
		addMessage(redirectAttributes, "删除WebArticleComment成功");
		return "redirect:"+Global.getAdminPath()+"/content/webArticleComment/?repage&articleId="+String.valueOf(articleId)+"&type="+String.valueOf(type);
	}

}