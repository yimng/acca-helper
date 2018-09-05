package com.thinkgem.jeesite.acca.web.register.web;

import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.acca.web.register.entity.WebAccaRegister;
import com.thinkgem.jeesite.acca.web.register.service.WebAccaRegisterService;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityUserRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebExamOpenCity;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebExamOpenCityService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import java.util.*;

/**
 * 代注册Controller
 * 
 * @author Young
 * @version 2016-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/register/webAccaRegister")
public class WebAccaRegisterController extends BaseController {

	@Autowired
	private WebAccaRegisterService webAccaRegisterService;
	@Autowired
	private WebExamOpenCityService cityService;

	@ModelAttribute
	public WebAccaRegister get(@RequestParam(required = false) String id) {
		WebAccaRegister entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = webAccaRegisterService.get(id);
		}
		if (entity == null) {
			entity = new WebAccaRegister();
		}
		return entity;
	}

	/**
	 * 获取注册列表
	 * 
	 * @param webAccaRegister
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("register:webAccaRegister:view")
	@RequestMapping(value = { "list", "" })
	public String list(WebAccaRegister webAccaRegister, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 获取登录用户信息
		// 通过用户id,获取绑定的附近城市列表
		// WebExamOpenCity openCity = new WebExamOpenCity();
		// openCity.setSysUserId(sysUserId);
		// List<WebExamOpenCity> cityList = cityService.findList(openCity);
		// if (cityList != null && cityList.size() > 0){
		// String citiesId = "";
		// for (WebExamOpenCity city:cityList){
		// citiesId += city.getCityId() + ",";
		// }
		// citiesId = citiesId.substring(0,citiesId.length() - 1);
		// webAccaRegister.getSqlMap().put("citiesId",citiesId);
		// }
		String sysUserId = UserUtils.getUser().getId();
		System.out.println("==sysUserId:" + sysUserId);
		List<WebCityUserRelation> cityList = cityService.findCityIdListByUserId(sysUserId);
		if (cityList != null && cityList.size() > 0) {
			String citiesId = "";
			for (WebCityUserRelation city : cityList) {
				citiesId += city.getCityId() + ",";
			}
			citiesId = citiesId.substring(0, citiesId.length() - 1);
			webAccaRegister.getSqlMap().put("citiesId", citiesId);
			System.out.println("==citiesId:" + citiesId);
		}
		// 获取各注册状态的数量列表
		List<Map<String, Integer>> countList = this.getCountList(webAccaRegister);
		Page<WebAccaRegister> page = webAccaRegisterService.findPage(new Page<WebAccaRegister>(request, response),
				webAccaRegister);
		model.addAttribute("countList", countList);
		model.addAttribute("page", page);
		// 判断注册类型,选择跳转的详细页面
		System.out.println("==End this list");
		if (webAccaRegister.getRegisterType() == ApiConstant.REGISTER_TYPE_FIA) {
			return "web/register/webFiaRegisterList";
		}
		return "web/register/webAccaRegisterList";
	}

	/**
	 * 获取注册详细信息
	 * 
	 * @param webAccaRegister
	 * @param model
	 * @return
	 */
	@RequiresPermissions("register:webAccaRegister:view")
	@RequestMapping(value = "form")
	public String form(WebAccaRegister webAccaRegister, Model model) {
		WebAccaRegister accaRegister = webAccaRegisterService.get(webAccaRegister);
		model.addAttribute("webAccaRegister", accaRegister);
		// 判断注册类型,选择跳转的详细页面
		if (accaRegister.getRegisterType() == ApiConstant.REGISTER_TYPE_FIA) {
			return "web/register/webFiaRegisterForm";
		}
		return "web/register/webAccaRegisterForm";
	}

	/**
	 * 填写注册信息,注册完成
	 * 
	 * @param webAccaRegister
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("register:webAccaRegister:edit")
	@RequestMapping(value = "updateRegisterPass")
	public String updateRegisterPass(WebAccaRegister webAccaRegister, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webAccaRegister)) {
			return form(webAccaRegister, model);
		}
		// 通过注册填写的学员名称,获取值,看是否存在,如果存在,不让保存
		WebAccaRegister newAccaRegister = new WebAccaRegister();
		newAccaRegister.setAccaRegisterName(webAccaRegister.getAccaRegisterName());
		newAccaRegister = webAccaRegisterService.get(newAccaRegister);
		if (newAccaRegister != null) {
			addMessage(redirectAttributes, "该学员号已经存在!");
			return "redirect:" + Global.getAdminPath() + "/register/webAccaRegister/form?accaRegisterId="
					+ webAccaRegister.getAccaRegisterId() + "&registerType" + webAccaRegister.getRegisterType();
		}
		// 更改状态为注册完成
		webAccaRegister.setStatus(ApiConstant.REGISTER_SUCCESS);
		webAccaRegisterService.updateRegisterPass(webAccaRegister);
		addMessage(redirectAttributes, "注册成功");
		return "redirect:" + Global.getAdminPath() + "/register/webAccaRegister/list?status=5&registerType="
				+ webAccaRegister.getRegisterType();
	}

	/**
	 * 进行审核
	 * 
	 * @param webAccaRegister
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("register:webAccaRegister:edit")
	@RequestMapping(value = "updateRegisterAudit")
	public String updateRegisterAudit(WebAccaRegister webAccaRegister, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webAccaRegister)) {
			return form(webAccaRegister, model);
		}
		User user = UserUtils.getUser();
		webAccaRegister.setCheckPersonId(user.getId());
		webAccaRegister.setCheckPersonName(user.getLoginName());
		webAccaRegister.setCheckTime(new Date());
		webAccaRegisterService.updateRegisterAudit(webAccaRegister);
		addMessage(redirectAttributes, "审核完成");
		return "redirect:" + Global.getAdminPath() + "/register/webAccaRegister/list?registerType="
				+ webAccaRegister.getRegisterType() + "&status=" + webAccaRegister.getStatus();
	}

	@RequiresPermissions("register:webAccaRegister:edit")
	@RequestMapping(value = "delete")
	public String delete(WebAccaRegister webAccaRegister, RedirectAttributes redirectAttributes) {
		webAccaRegisterService.delete(webAccaRegister);
		addMessage(redirectAttributes, "删除代注册成功");
		return "redirect:" + Global.getAdminPath() + "/register/webAccaRegister/?repage";
	}

	/**
	 * 不通过的弹出框
	 * 
	 * @param webAccaRegister
	 * @param iframeId
	 * @param model
	 * @return
	 */
	@RequiresPermissions("register:webAccaRegister:edit")
	@RequestMapping(value = "notPass")
	public String notPass(WebAccaRegister webAccaRegister, String iframeId, Model model) {
		model.addAttribute("iframeId", iframeId);
		model.addAttribute("webAccaRegister", webAccaRegister);
		return "web/register/notPass";
	}

	/**
	 * 获取每个状态的数量
	 * 
	 * @param webAccaRegister
	 * @return
	 */
	private List<Map<String, Integer>> getCountList(WebAccaRegister webAccaRegister) {
		List<Map<String, Integer>> resultList = webAccaRegisterService.getStatusCount(webAccaRegister);
		List<Map<String, Integer>> countList = new ArrayList<Map<String, Integer>>();
		for (int i = 1; i <= 5; i++) {
			for (int k = 0; k < resultList.size(); k++) {
				Map<String, Integer> map = resultList.get(k);
				if (map.get("status").equals(i)) {
					countList.add(map);
					break;
				} else if (k == (resultList.size() - 1)) {
					Map<String, Integer> newMap = new HashMap<String, Integer>();
					newMap.put("status", i);
					newMap.put("num", 0);
					countList.add(newMap);
				}
			}
		}
		return countList;
	}
}