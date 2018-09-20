/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.web;

import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.exam.entity.*;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamSeasonService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
 * WebExamSeasonController
 * @author Ivan
 * @version 2016-08-27
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/webExamSeason")
public class WebExamSeasonController extends BaseController {

	@Autowired
	private WebExamSeasonService webExamSeasonService;
	
	@Autowired
	private WebExamService examService;
	
	@ModelAttribute
	public WebExamSeason get(@RequestParam(required=false) String id) {
		WebExamSeason entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webExamSeasonService.get(id);
		}
		if (entity == null){
			entity = new WebExamSeason();
		}
		return entity;
	}
	
	@RequiresPermissions("exam:webExamSeason:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebExamSeason webExamSeason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WebExamSeason> page = webExamSeasonService.findPage(new Page<WebExamSeason>(request, response,-1), webExamSeason); 
		logger.info("list:{}",page.getList());
		model.addAttribute("page", page);
		return "web/exam/webExamSeasonList";
	}

	@RequiresPermissions("exam:webExamSeason:view")
	@RequestMapping(value = "form")
	public String form(WebExamSeason webExamSeason, Model model) {
		WebExam exam = new WebExam();
		exam.setExamType(Constants.ExamType.officialM);
		exam.setExamType2(Constants.ExamType.officialPen);
		List<SmallCourse> courses = examService.selectCoursesByType(exam);
		if(!StringUtils.isEmpty(webExamSeason.getExamSeasonStr())){
			List<WebExamSeason> list = webExamSeasonService.findList(webExamSeason);
			WebExamSeason examSeason = list.get(0);
			for(SmallCourse sc : courses){
				for(WebExamCourseForSeason season : examSeason.getExamCourselist()){
					if(season.getExamCourseId().equals(sc.getExamCourseId())){
						sc.setChecked(true);
						sc.setPrePrice(season.getPrePrice());
						sc.setNormalPrice(season.getNormalPrice());
						sc.setPostPrice(season.getPostPrice());
						for(WebExamVersion we : sc.getExamVersions()){
							if(season.getVersionList() != null) {
                                for(WebExamVersion sea : season.getVersionList()){
                                    if(sea.getExamVersionId().equals(we.getExamVersionId())){
                                        we.setChecked(true);
                                    }
                                }
                            }
						}
					}
				}
			}
			logger.info("list:"+list);
			model.addAttribute("courses",courses);
			model.addAttribute("examSeason",examSeason);
		} else {
			model.addAttribute("courses",courses);
			model.addAttribute("examSeason",webExamSeason);
		}
		return "web/exam/webExamSeasonForm";
	}
	
	@RequestMapping("validata")
	@ResponseBody
	public ModelMap validata(WebExamSeason webExamSeason){
		ModelMap map = new ModelMap();
		List<WebExamSeason> allList = webExamSeasonService.findAllList(webExamSeason);
		if(StringUtils.isEmpty(webExamSeason.getOldSeasonStr())){
			if(allList != null && allList.size() != 0){
				map.put("result", false);
				map.put("msg", "保存失败，已添加过相同的考季");
				return map;
			}
		} else if(!(webExamSeason.getOldSeasonStr().equals(webExamSeason.getExamSeasonStr()))) {
			if(allList != null && allList.size() != 0){
				map.put("result", false);
				map.put("msg", "保存失败，已添加过相同的考季");
				return map;
			}
		}
		boolean selectedCourse = false;
		for(WebExamCourseForSeason c: webExamSeason.getExamCourselist()) {
		    if(c.getExamCourseId() != null) {
                selectedCourse = true;
                break;
            }
        }
		if(!selectedCourse){
			map.put("result", false);
			map.put("msg", "保存失败！未选中相关版本");
			return map;
		}
		map.put("result", true);
		return map;
	}
	

	@RequiresPermissions("exam:webExamSeason:edit")
	@RequestMapping(value = "save")
	public String save(WebExamSeason webExamSeason, Model model, RedirectAttributes redirectAttributes) {
		/*List<WebExamSeason> allList = webExamSeasonService.findAllList(webExamSeason);
		if(StringUtils.isEmpty(webExamSeason.getOldSeasonStr())){
			if(allList != null && allList.size() != 0){
				addMessage(redirectAttributes, "保存失败，已添加过相同的考季");
				return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason/?repage";
			}
		} else if(!(webExamSeason.getOldSeasonStr().equals(webExamSeason.getExamSeasonStr()))) {
			if(allList != null && allList.size() != 0){
				addMessage(redirectAttributes, "保存失败，已添加过相同的考季");
				return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason/?repage";
			}
		}
		if(examCourseIds == null || examCourseIds.length == 0){
			addMessage(redirectAttributes, "保存失败！未选中相关课程");
			return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason/?repage";
		}
		if(examVersionStrs == null || examVersionStrs.length == 0){
			addMessage(redirectAttributes, "保存失败！未选中相关版本");
			return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason/?repage";
		}*/


//		for(Long examCourseId : examCourseIds){
//			WebExamSeason season = new WebExamSeason();
//			season.setExamCourseId(examCourseId);
//			season.setExamSeasonStr(webExamSeason.getExamSeasonStr());
//			List<SmallVersion> versions = new ArrayList<SmallVersion>();
//			if(examVersionStrs!= null ){
//				if(examVersionStrs.length != 0 && examVersionStrs.length == 3 && examVersionStrs[0].split(",").length == 1){
//					String[] array = examVersionStrs;
//					if(Long.valueOf(array[0]).equals(examCourseId)){
//						SmallVersion version = new SmallVersion();
//						version.setExamVersionId(Long.valueOf(array[1]));
//						version.setExamVersionName(array[2]);
//						versions.add(version);
//					}
//				} else {
//					for(String s : examVersionStrs){
//						String[] array = s.split(",");
//						if(Long.valueOf(array[0]).equals(examCourseId)){
//							SmallVersion version = new SmallVersion();
//							version.setExamVersionId(Long.valueOf(array[1]));
//							version.setExamVersionName(array[2]);
//							versions.add(version);
//						}
//					}
//				}
//			}
//			if(versions.size() != 0){
//				season.setExamVersionJson(JsonMapper.toJsonString(versions));
//			}
//			seasons.add(season);
//		}

        List<WebExamSeason> seasons = new ArrayList<WebExamSeason>();
        for(WebExamCourseForSeason courseForSeason : webExamSeason.getExamCourselist()) {
            if (courseForSeason.getExamCourseId() == null) {
                continue;
            }
            WebExamSeason examSeason = new WebExamSeason();
            examSeason.setExamSeasonStr(webExamSeason.getExamSeasonStr());
            examSeason.setExamCourseId(courseForSeason.getExamCourseId());
            examSeason.setExamCourse(courseForSeason.getExamCourse());
            List<SmallVersion> versions = new ArrayList<>();
            if (courseForSeason.getVersionList() != null) {
                for (WebExamVersion examVersion : courseForSeason.getVersionList()) {
                    if (examVersion.getExamVersionId() == null) {
                        continue;
                    }
                    SmallVersion version = new SmallVersion();
                    version.setExamVersionId(examVersion.getExamVersionId());
                    version.setExamVersionName(examVersion.getExamVersionName());
                    versions.add(version);
                }
            }
            examSeason.setExamVersionJson(JsonMapper.toJsonString(versions));
            examSeason.setPreSignup(webExamSeason.getPreSignup());
            examSeason.setSignUp(webExamSeason.getSignUp());
            examSeason.setPostSignup(webExamSeason.getPostSignup());
            if (StringUtils.isNotEmpty(courseForSeason.getPrePrice())) {
                examSeason.setPrePrice(courseForSeason.getPrePrice());
            }
            if (StringUtils.isNotEmpty(courseForSeason.getNormalPrice())) {
                examSeason.setNormalPrice(courseForSeason.getNormalPrice());
            }
            if (StringUtils.isNotEmpty(courseForSeason.getPostPrice())) {
                examSeason.setPostPrice(courseForSeason.getPostPrice());
            }
            seasons.add(examSeason);
        }

		webExamSeasonService.saveSeasons(webExamSeason, seasons);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason";
	}
	
	@RequiresPermissions("exam:webExamSeason:edit")
	@RequestMapping(value = "delete")
	public String delete(WebExamSeason webExamSeason, RedirectAttributes redirectAttributes) {
		//获取该考季的考试列表
		String examSeasonStr = webExamSeason.getExamSeasonStr();
		WebExam exam = new WebExam();
		exam.getSqlMap().put("seasonStr",examSeasonStr);
		List<WebExam> webExams = examService.selectExamByTypeAndSeason(exam);
		if (webExams != null && webExams.size() > 0){
			addMessage(redirectAttributes, "操作失败，该考季已经有考试了,不允许删除!");
			return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason";
		}
		webExamSeasonService.delete(webExamSeason);
		addMessage(redirectAttributes, "删除考季成功!");
		return "redirect:"+Global.getAdminPath()+"/exam/webExamSeason";
	}

}