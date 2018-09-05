/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

import com.thinkgem.jeesite.acca.web.content.entity.WebAccaEnglishWord;
import com.thinkgem.jeesite.acca.web.content.service.WebAccaEnglishWordService;
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
 * ACCA财经词汇Controller
 * @author Young
 * @version 2016-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/englishword/webAccaEnglishWord")
public class WebAccaEnglishWordController extends BaseController {

	@Autowired
	private WebAccaEnglishWordService webAccaEnglishWordService;
	
	@ModelAttribute
	public WebAccaEnglishWord get(@RequestParam(required=false) String id) {
		WebAccaEnglishWord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webAccaEnglishWordService.get(id);
		}
		if (entity == null){
			entity = new WebAccaEnglishWord();
		}
		return entity;
	}
	
	@RequiresPermissions("englishword:webAccaEnglishWord:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebAccaEnglishWord webAccaEnglishWord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebAccaEnglishWord> page = webAccaEnglishWordService.findPage(new Page<WebAccaEnglishWord>(request, response), webAccaEnglishWord); 
		model.addAttribute("page", page);
		return "web/content/word/webAccaEnglishWordList";
	}

	@RequiresPermissions("englishword:webAccaEnglishWord:view")
	@RequestMapping(value = "form")
	public String form(WebAccaEnglishWord webAccaEnglishWord, Model model) {
		model.addAttribute("webAccaEnglishWord", webAccaEnglishWord);
		return "web/content/word/webAccaEnglishWordForm";
	}

	@RequiresPermissions("englishword:webAccaEnglishWord:edit")
	@RequestMapping(value = "save")
	public String save(WebAccaEnglishWord webAccaEnglishWord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webAccaEnglishWord)){
			return form(webAccaEnglishWord, model);
		}
		String id = webAccaEnglishWord.getId();
		String message = webAccaEnglishWordService.saveOrUpdate(webAccaEnglishWord);
		addMessage(redirectAttributes, message);
		return "redirect:"+Global.getAdminPath()+"/englishword/webAccaEnglishWord/?repage";
	}
	
	@RequiresPermissions("englishword:webAccaEnglishWord:edit")
	@RequestMapping(value = "delete")
	public String delete(WebAccaEnglishWord webAccaEnglishWord, RedirectAttributes redirectAttributes) {
		webAccaEnglishWordService.delete(webAccaEnglishWord);
		addMessage(redirectAttributes, "删除ACCA财经词汇成功");
		return "redirect:"+Global.getAdminPath()+"/englishword/webAccaEnglishWord/?repage";
	}

}