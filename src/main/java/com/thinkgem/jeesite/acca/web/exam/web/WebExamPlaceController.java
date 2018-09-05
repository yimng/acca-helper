/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.web;

import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExam;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamPlace;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamPlaceService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamService;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 考点类Controller
 * @author Michael
 * @version 2016-08-28
 */
@Controller
@RequestMapping(value = "${adminPath}/web/place")
public class WebExamPlaceController extends BaseController {

	@Autowired
	private WebExamPlaceService webExamPlaceService;
	@Autowired
	private WebExamService examService;
	@Autowired
	private SystemService systemService;
	
	/*@ModelAttribute
	public WebExamPlace get(@RequestParam(required=false) String examPlaceId, @RequestParam(required=false) Integer examType) {
		WebExamPlace entity = null;
		if (StringUtils.isNotBlank(examPlaceId)){
			entity = webExamPlaceService.get(examPlaceId);
		}
		if (entity == null){
			entity = new WebExamPlace();
			entity.setExamType(examType);
		}
		return entity;
	}*/
	
	@RequiresPermissions("web:place:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebExamPlace webExamPlace, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(webExamPlace.getExamType() == null || webExamPlace.getExamType() == 0){
			webExamPlace.setExamType(Constants.ExamType.self);
		}
		Page<WebExamPlace> page = webExamPlaceService.findPage(new Page<WebExamPlace>(request, response), webExamPlace); 
		model.addAttribute("page", page);
		webExamPlace.setMenuExamType(webExamPlace.getExamType());
		return "web/exam/place/webExamPlaceList";
	}
	
	@RequiresPermissions("web:place:view")
	@RequestMapping(value = "macList")
	public String macList(WebExamPlace webExamPlace, HttpServletRequest request, HttpServletResponse response, Model model) {
		webExamPlace.setExamType(Constants.ExamType.officialM);
		return list(webExamPlace, request, response, model);
	}

	@RequiresPermissions("web:place:view")
	@RequestMapping(value = "penList")
	public String penList(WebExamPlace webExamPlace, HttpServletRequest request, HttpServletResponse response, Model model) {
		webExamPlace.setExamType(Constants.ExamType.officialPen);
		return list(webExamPlace, request, response, model);
	}
	
	@RequiresPermissions("web:place:view")
	@RequestMapping(value = "form")
	public String form(WebExamPlace webExamPlace, Model model) {
		WebExamPlace wep = null;
		if(webExamPlace.getExamPlaceId() == null){
			wep = new WebExamPlace();
		} else {
			wep = webExamPlaceService.get(webExamPlace);
		}
		Integer menuExamType = webExamPlace.getMenuExamType();
		//获取系统用户列表
		List<User> userList = systemService.findUser(new User());
		model.addAttribute("userList",userList);
		WebExam we = new WebExam();
		we.setExamType(menuExamType);
		model.addAttribute("citys", examService.selectCitysByType(we));
		wep.setMenuExamType(menuExamType);
		model.addAttribute("webExamPlace", wep);
		return "web/exam/place/webExamPlaceForm";
	}

	@RequiresPermissions("web:place:edit")
	@RequestMapping(value = "save")
	public String save(WebExamPlace webExamPlace, Model model, HttpServletRequest request, HttpServletResponse response) {
		/*if (!beanValidator(model, webExamPlace)){
			return form(webExamPlace, model);
		}*/
		webExamPlace.setExamType(webExamPlace.getMenuExamType());
		webExamPlaceService.save(webExamPlace);
		addMessage(model, "保存考点成功");
		WebExamPlace wep = new WebExamPlace();
		wep.setExamType(webExamPlace.getMenuExamType());
		return list(wep, request, response, model);
	}
	
	@RequiresPermissions("web:place:edit")
	@RequestMapping(value = "delete")
	public String delete(WebExamPlace webExamPlace, Model model, Integer menuExamType, HttpServletRequest request, HttpServletResponse response) {
		WebExam we = new WebExam();
		we.setExamPlaceId(webExamPlace.getExamPlaceId());
		List<WebExam> list = examService.findList(we);
		if(list != null && list.size() != 0){
			addMessage(model, "删除失败！此考点已关联考试，不可删除！");
		} else {
			webExamPlaceService.delete(webExamPlace);
			addMessage(model, "删除考点成功");
		}
		WebExamPlace wep = new WebExamPlace();
		wep.setExamType(menuExamType);
		return list(wep, request, response, model);
	}

}