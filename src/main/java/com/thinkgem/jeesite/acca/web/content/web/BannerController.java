/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

import com.thinkgem.jeesite.acca.web.content.entity.Banner;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticle;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleCategory;
import com.thinkgem.jeesite.acca.web.content.service.BannerService;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleCategoryService;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * banner设置Controller
 * @author Young
 * @version 2016-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/banner")
public class BannerController extends BaseController {

	@Autowired
	private BannerService bannerService;
	@Autowired
	private WebArticleService articleService;
	@Autowired
	private WebArticleCategoryService articleCategoryService;
	
	@ModelAttribute
	public Banner get(@RequestParam(required=false) String id) {
		Banner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bannerService.get(id);
		}
		if (entity == null){
			entity = new Banner();
		}
		return entity;
	}
	
	@RequiresPermissions("banner:banner:view")
	@RequestMapping(value = {"list", ""})
	public String list(Banner banner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Banner> page = bannerService.findPage(new Page<Banner>(request, response), banner); 
		model.addAttribute("page", page);
		return "web/content/banner/bannerList";
	}

	@RequiresPermissions("banner:banner:view")
	@RequestMapping(value = "form")
	public String form(Banner banner, Model model) {
		//获取分类列表
		List<WebArticleCategory> categoryList = articleCategoryService.findList(new WebArticleCategory());
		if (banner.getBannerId() != null){
			banner = bannerService.get(banner);
			WebArticle webArticle = new WebArticle();
			webArticle.setArticleId(banner.getArticleId());
			webArticle = articleService.get(webArticle);
			if (webArticle != null){
				model.addAttribute("articleCategoryName",webArticle.getCategoryName());
				model.addAttribute("articleCategoryId", webArticle.getArticleCategoryId());
			}
		}
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("banner", banner);
		return "web/content/banner/bannerForm";
	}

	/**
	 * 保存或更新banner信息
	 * @param banner
	 * @param model
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("banner:banner:edit")
	@RequestMapping(value = "save")
	public String save(Banner banner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, banner)){
			return form(banner, model);
		}
		//设置默认的位置为首页
		banner.setPosition(0);
		//现在通过时间判断状态,把状态都默认为0正常
		banner.setStatus(0);
		String message = bannerService.saveOrUpdate(banner);
		addMessage(redirectAttributes, message);
		return "redirect:"+Global.getAdminPath()+"/banner/list?status=0";
	}

	/**
	 * 删除banner
	 * @param banner
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("banner:banner:edit")
	@RequestMapping(value = "delete")
	public String delete(Banner banner, RedirectAttributes redirectAttributes) {
		bannerService.delete(banner);
		addMessage(redirectAttributes, "删除banner设置成功");
		return "redirect:"+Global.getAdminPath()+"/banner/?repage";
	}

	/**
	 * 获取文章分类类型
	 * @param model
     * @return
     */
	@RequiresPermissions("banner:banner:view")
	@RequestMapping(value = "/getArticlesByCategoryId")
	@ResponseBody
	public List<WebArticle> getArticlesByCategoryId(Model model, String articleCategoryId) {
		List<WebArticle> articleList = new ArrayList<WebArticle>();
		WebArticle condition = new WebArticle();
		if (StringUtils.isNotEmpty(articleCategoryId)){
			condition.setArticleCategoryId(Long.valueOf(articleCategoryId));
			articleList = articleService.findList(condition);
		}
		return articleList;
	}

}