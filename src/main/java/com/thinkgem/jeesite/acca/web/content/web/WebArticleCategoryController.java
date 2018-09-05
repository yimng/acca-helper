/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

import com.thinkgem.jeesite.acca.constant.WebConstant;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleCategory;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleCategoryService;
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
 * 文章分类Controller
 * @author Young
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/webArticleCategory")
public class WebArticleCategoryController extends BaseController {

	@Autowired
	private WebArticleCategoryService webArticleCategoryService;
	
	@ModelAttribute
	public WebArticleCategory get(@RequestParam(required=false) String id) {
		WebArticleCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webArticleCategoryService.get(id);
		}
		if (entity == null){
			entity = new WebArticleCategory();
		}
		return entity;
	}

	/**
	 * 文章分类列表
	 * @param webArticleCategory
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequiresPermissions("articlecategory:webArticleCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebArticleCategory webArticleCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebArticleCategory> page = webArticleCategoryService.findPage(new Page<WebArticleCategory>(request, response), webArticleCategory); 
		model.addAttribute("page", page);
		return "web/content/article/webArticleCategoryList";
	}

	@RequiresPermissions("articlecategory:webArticleCategory:view")
	@RequestMapping(value = "form")
	public String form(WebArticleCategory webArticleCategory, Model model) {
		model.addAttribute("webArticleCategory", webArticleCategory);
		return "web/content/article/webArticleCategoryForm";
	}

	/**
	 * 保存文章分类
	 * @param webArticleCategory
	 * @param model
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("articlecategory:webArticleCategory:edit")
	@RequestMapping(value = "save")
	public String save(WebArticleCategory webArticleCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webArticleCategory)){
			return form(webArticleCategory, model);
		}
		if (webArticleCategory.getArticleCategoryId() == null){
			//设置类型和是否为系统数据的默认值
			webArticleCategory.setSysData(WebConstant.ARTICLECATEGORY_NOT_SYSDATA);
			webArticleCategoryService.save(webArticleCategory);
			addMessage(redirectAttributes, "保存文章分类成功");
		} else {
			webArticleCategoryService.update(webArticleCategory);
			addMessage(redirectAttributes, "修改文章分类成功");
		}
		return "redirect:"+Global.getAdminPath()+"/webArticleCategory/?repage";
	}

	/**
	 * 删除文章分类
	 * @param webArticleCategory
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("articlecategory:webArticleCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(WebArticleCategory webArticleCategory, RedirectAttributes redirectAttributes) {
		Boolean flag = webArticleCategoryService.deleteCategory(webArticleCategory);
		if (flag){
			addMessage(redirectAttributes, "删除文章分类成功");
		} else {
			addMessage(redirectAttributes, "该分类下已经有文章,禁止删除!");
		}
		return "redirect:"+Global.getAdminPath()+"/webArticleCategory/?repage";
	}

}