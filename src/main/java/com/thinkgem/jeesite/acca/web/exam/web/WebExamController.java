package com.thinkgem.jeesite.acca.web.exam.web;

import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.exam.entity.*;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamPlaceService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamSeasonService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamSignupService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/web/exam")
public class WebExamController extends BaseController {

	@Autowired
	private WebExamService examService;
	@Autowired
	private WebExamSignupService signupService;
	@Autowired
	private WebExamSeasonService seasonService;
	@Autowired
	private WebExamPlaceService examPlaceService;
	
	@RequiresPermissions("web:exam:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebExam webExam, HttpServletRequest request, HttpServletResponse response, Model model){
		//获取登录用户的信息
		User user = UserUtils.getUser();
		String userId = user.getId();
		//先通过用户id查询是否有绑定考点
		WebExamPlace place = new WebExamPlace();
		place.setSysUserId(userId);
		//查询考点id列表
		List<WebExamPlace> placesList;
		//当为自有考试的时候,去查询考点信息
		if(webExam.getExamType() == null){
			webExam.setExamType(Constants.ExamType.self);
		}
		placesList = examPlaceService.findList(place);
		//如果id列表不为空
		if (placesList != null && placesList.size() > 0){
			webExam.getSqlMap().put("sysUserId",userId);
		}
		if(webExam.getExamFlag() == null){
			webExam.setExamFlag(Constants.ExamFlag.now);
		}

		//获取考点列表
		List<WebExamPlace> places = examService.selectPlaceByType(webExam);
		model.addAttribute("places", places);
		if (places != null && places.size() > 0) {
            webExam.setPlaces(places);
        }
		//获取考试列表
		Page<WebExam> page = examService.findPage(new Page<WebExam>(request, response), webExam);
		List<WebExam> examList = page.getList();
		if(examList != null && examList.size() != 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", examList);
			map.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);
			List<WebExam> countList = examService.countSignups(map);
			if(countList != null && countList.size() != 0){
				for(WebExam we : examList){
					for(WebExam weCount : countList){
						if(we.getExamId().equals(weCount.getExamId())){
							we.setSignupNum(weCount.getSignupNum());
						}
					}
				}
			}
			
			List<WebExam> courseList = examService.findCourseByIds(examList);
			if(courseList != null && courseList.size() != 0){
				for(WebExam we : examList){
					for(WebExam weCourse : courseList){
						if(we.getExamId().equals(weCourse.getExamId())){
							we.setCourses(weCourse.getCourses());
						}
					}
				}
			}
		}
		model.addAttribute("page", page);
		return "web/exam/exam/list";
	}
	
	@RequiresPermissions("web:exam:view")
	@RequestMapping(value = "macList")
	public String macList(WebExam webExam, HttpServletRequest request, HttpServletResponse response, Model model){
		webExam.setExamType(Constants.ExamType.officialM);
		return list(webExam, request, response, model);
	}
	
	@RequiresPermissions("web:exam:view")
	@RequestMapping(value = "penList")
	public String penList(WebExam webExam, HttpServletRequest request, HttpServletResponse response, Model model){
		webExam.setExamType(Constants.ExamType.officialPen);
		return list(webExam, request, response, model);
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "form")
	public String form(WebExam webExam, Model model){
		if(webExam.getExamType() == Constants.ExamType.officialM){
			return officialForm(webExam, model);
		} else if(webExam.getExamType() == Constants.ExamType.officialPen) {
			return officialForm(webExam, model);
		} else {
			return freeForm(webExam, model);
		}
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "officialForm")
	public String officialForm(WebExam webExam, Model model){
		//获取登录用户的信息
		User user = UserUtils.getUser();
		String userId = user.getId();
		//先通过用户id查询是否有绑定考点
		WebExamPlace place = new WebExamPlace();
		place.setSysUserId(userId);
		//查询考点id列表
		List<WebExamPlace> placesList = examPlaceService.findList(place);
		//如果id列表不为空
		if (placesList != null && placesList.size() > 0){
			webExam.setPlaces(placesList);
			webExam.getSqlMap().put("sysUserId",userId);
		}
		model.addAttribute("citys", JsonMapper.toJsonString(examService.selectCitysByType(webExam)));
		model.addAttribute("places", JsonMapper.toJsonString(examService.selectPlaceByType(webExam)));
		List<SmallCourse> courses = examService.selectCoursesByType(webExam);
		model.addAttribute("courses", courses);
		model.addAttribute("seasons", examService.selectSeasons(new WebExam()));
		if(webExam.getExamId() != null){
			WebExam we = examService.get(webExam);
			List<WebExam> examList = examService.selectExamByTypeAndSeason(we);
			for(SmallCourse sc : courses){
				for(WebExam exa : examList){
					if(exa.getExamCourseId().equals(sc.getExamCourseId())){
						sc.setExamStartTime(exa.getExamStartTime());
						sc.setExamEndTime(exa.getExamEndTime());
						sc.setExamSignupEndtime(exa.getExamSignupEndtime());
						sc.setExamId(exa.getExamId());
					}
				}
			}
			we.setExamSeasonStr(TimeUtils.DateToStr(we.getExamStartTime(), TimeUtils.dateFormat3));
			we.setExamFlag(webExam.getExamFlag());
			model.addAttribute("exam", we);
		} else {
			model.addAttribute("exam", webExam);
		}
		return "web/exam/exam/officialForm";
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "freeForm")
	public String freeForm(WebExam webExam, Model model){
		//获取登录用户的信息
		User user = UserUtils.getUser();
		String userId = user.getId();
		//先通过用户id查询是否有绑定考点
		WebExamPlace place = new WebExamPlace();
		place.setSysUserId(userId);
		//查询考点id列表
		List<WebExamPlace> placesList = examPlaceService.findList(place);
		//如果id列表不为空
		if (placesList != null && placesList.size() > 0){
			webExam.setPlaces(placesList);
			webExam.getSqlMap().put("sysUserId",userId);
		}
		model.addAttribute("citys", JsonMapper.toJsonString(examService.selectCitysByType(webExam)));
		List<SmallCourse> courses = examService.selectCoursesByType(webExam);
		model.addAttribute("courses", courses);
		model.addAttribute("places", JsonMapper.toJsonString(examService.selectPlaceByType(webExam)));
		WebExam exam = null;
		if(webExam.getExamId() != null){
			exam = examService.get(webExam);
			List<SmallCourse> listCourses = examService.selectCoursesByExamId(webExam);
			for(SmallCourse sc : courses){
				for(SmallCourse course : listCourses){
					if(sc.getExamCourseId().equals(course.getExamCourseId())){
						sc.setChecked(true);
						for(WebExamVersion wev : sc.getExamVersions()){
							if(course.getExamVersions() != null){
								if(course.getExamVersions().contains(wev)){
									wev.setChecked(true);
								}
							}
						}
					}
				}
			}
			exam.setCourses(listCourses);
			exam.setExamFlag(webExam.getExamFlag());
		} else {
			exam = webExam;
		}
		model.addAttribute("exam", exam);
		return "web/exam/exam/freeForm";
	}
	
	@RequestMapping(value = "validataOfficial")
	@ResponseBody
	public ModelMap validataOfficial(WebExam webExam){
		WebExam examTmp = new WebExam();
		examTmp.setExamType(webExam.getExamType());
		examTmp.setExamPlaceId(webExam.getExamPlaceId());
		ModelMap map = new ModelMap();
		if(webExam.getExamId() == null){
			examTmp.setExamStartTime(TimeUtils.StrToDate(webExam.getExamSeasonStr(), TimeUtils.dateFormat3));
			List<WebExam> es = examService.selectExamByTypeAndSeason(examTmp);
			if(es != null && es.size()!=0){
				map.put("result", false);
				map.put("msg", "保存失败，已添加过相同的考季和考点！");
				return map;
			}
		} else {
			examTmp.setExamStartTime(TimeUtils.StrToDate(webExam.getExamSeasonStr(), TimeUtils.dateFormat3));
			if(!(webExam.getExamPlaceId().equals(webExam.getOldExamPlaceId())
					&&(TimeUtils.DateToStr(webExam.getOldExamStartTime(), TimeUtils.dateFormat3).equals(webExam.getExamSeasonStr())))){
				List<WebExam> es = examService.selectExamByTypeAndSeason(examTmp);
				if( es != null && es.size()!=0){
					map.put("result", false);
					map.put("msg", "保存失败，已添加过相同的考季和考点！");
					return map;
				}
			}
		}
		List<WebExam> list = webExam.getList();
		Date d = new Date();
		for(WebExam we : list){
			if(!(TimeUtils.isEqualsDate(we.getExamStartTime(), we.getExamEndTime()))){
				map.put("result", false);
				map.put("msg", "保存失败，考试开始时间和结束时间应为同一天！");
				return map;
			} else if(!(TimeUtils.DateToStr(we.getExamStartTime(), TimeUtils.dateFormat3).equals(webExam.getExamSeasonStr()))){
				map.put("result", false);
				map.put("msg", "保存失败，考试开始时间应该与考季一致！");
				return map;
			} else if(TimeUtils.compare(we.getExamStartTime(), we.getExamSignupEndtime()) != 0){
				map.put("result", false);
				map.put("msg", "保存失败，考试开始时间应该大于报名截止时间！");
				return map;
			} else if(TimeUtils.compare(we.getExamSignupEndtime(), d) != 0){
				map.put("result", false);
				map.put("msg", "保存失败，报名截止时间应该大于当前时间！");
				return map;
			} else if(TimeUtils.compare(we.getExamEndTime(), we.getExamStartTime()) != 0){
				map.put("result", false);
				map.put("msg", "保存失败，考试结束时间应该大于考试开始时间！");
				return map;
			} 
		}
		map.put("result", true);
		return map;
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "editOfficial")
	public String editOfficial(WebExam webExam, HttpServletRequest request, HttpServletResponse response, Model model){
		WebExamSeason webExamSeason = new WebExamSeason();
		webExamSeason.setExamSeasonStr(webExam.getExamSeasonStr());
		List<WebExamSeason> seasons = seasonService.findAllList(webExamSeason);
		List<WebExam> list = webExam.getList();
		for(WebExam we : list){
			for(WebExamCourseForSeason season : seasons.get(0).getExamCourselist()){
				if(we.getExamCourseId().equals(season.getExamCourseId())){
					we.setExamVersionJson(season.getExamVersionJson());
				}
			}
		}
		examService.editOfficial(webExam);
		webExam.setExamPlaceId(null);
		addMessage(model, "保存成功");
		return list(webExam, request, response, model);
	}
	
	@RequestMapping("validataFree")
	@ResponseBody
	public ModelMap validataFree(WebExam webExam, Long[] examCourseIds, String[] examVersionStrs){
		Date examStartTime = webExam.getExamStartTime();
		Date examEndTime = webExam.getExamEndTime();
		ModelMap map = new ModelMap();
		if(!(TimeUtils.isEqualsDate(examStartTime, examEndTime))){
			map.put("result", false);
			map.put("msg", "保存失败，考试开始时间和结束时间应为同一天！");
			return map;
		} else if(TimeUtils.compare(examEndTime, examStartTime) != 0){
			map.put("result", false);
			map.put("msg", "保存失败，考试结束时间应该大于考试开始时间！");
			return map;
		} else if(TimeUtils.compare(examStartTime, webExam.getExamSignupEndtime()) != 0){
			map.put("result", false);
			map.put("msg", "保存失败，考试开始时间应该大于报名截止时间！");
			return map;
		}else if(TimeUtils.compare(webExam.getExamSignupEndtime(), new Date()) != 0){
			map.put("result", false);
			map.put("msg", "保存失败，报名截止时间应该大于当前时间！");
			return map;
		}
		if(examCourseIds == null || examCourseIds.length == 0){
			map.put("result", false);
			map.put("msg", "保存失败！未选中相关课程");
			return map;
		}
		/*if(examVersionStrs == null || examVersionStrs.length == 0){
			map.put("result", false);
			map.put("msg", "保存失败！未选中相关版本");
			return map;
		}*/
		map.put("result", true);
		return map;
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "edit")
	public String edit(WebExam webExam, Long[] examCourseIds, String[] examVersionStrs, RedirectAttributes redirectAttributes, Model model){
		/*Date examStartTime = webExam.getExamStartTime();
		Date examEndTime = webExam.getExamEndTime();
		if(!(TimeUtils.isEqualsDate(examStartTime, examEndTime))){
			addMessage(model, "保存失败，考试开始时间和结束时间应为同一天！");
			return freeForm(webExam, model);
		} else if(TimeUtils.compare(examEndTime, examStartTime) != 0){
			addMessage(model, "保存失败，考试结束时间应该大于考试开始时间！");
			return freeForm(webExam, model);
		} else if(TimeUtils.compare(examStartTime, webExam.getExamSignupEndtime()) != 0){
			addMessage(model, "保存失败，考试开始时间应该大于报名截止时间！");
			return freeForm(webExam, model);
		}
		if(examCourseIds == null || examCourseIds.length == 0){
			addMessage(model, "保存失败！未选中相关课程");
			return freeForm(webExam, model);
		}
		if(examVersionStrs == null || examVersionStrs.length == 0){
			addMessage(model, "保存失败！未选中相关版本");
			return freeForm(webExam, model);
		}*/
		List<WebExamCourseRelation> relations = new ArrayList<WebExamCourseRelation>();
		for(Long examCourseId : examCourseIds){
			WebExamCourseRelation relation = new WebExamCourseRelation();
			relation.setExamCourseId(examCourseId);
			List<SmallVersion> versions = new ArrayList<SmallVersion>();
			if(examVersionStrs!= null){
				if(examVersionStrs.length != 0 && examVersionStrs.length == 3 && examVersionStrs[0].split(",").length == 1){
					String[] array = examVersionStrs;
					if(Long.valueOf(array[0]).equals(examCourseId)){
						SmallVersion version = new SmallVersion();
						version.setExamVersionId(Long.valueOf(array[1]));
						version.setExamVersionName(array[2]);
						versions.add(version);
					}
				} else {
					for(String s : examVersionStrs){
						String[] array = s.split(",");
						if(Long.valueOf(array[0]).equals(examCourseId)){
							SmallVersion version = new SmallVersion();
							version.setExamVersionId(Long.valueOf(array[1]));
							version.setExamVersionName(array[2]);
							versions.add(version);
						}
					}
				}
			}
			
			if(versions.size() != 0){
				relation.setExamVersionJson(JsonMapper.toJsonString(versions));
			}
			relations.add(relation);
		}
		examService.edit(webExam, relations);
		return "redirect:"+Global.getAdminPath()+"/web/exam/?repage";
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "seatForm")
	public String seatForm(WebExam webExam, Model model){
		model.addAttribute("exam", examService.get(webExam));
		return "web/exam/exam/seatForm";
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "editSeat")
	public String editSeat(WebExam webExam, Integer addOrNo, Integer addSeats, Model model){
		WebExam exam = examService.get(webExam);
		if(addOrNo == 1){
			exam.setTotalSeats(exam.getTotalSeats() + addSeats);
		} else {
			Integer us = exam.getUsedSeats(); 
			if(us == null){
				us = 0;
			}
			if(exam.getTotalSeats() - us < addSeats){
				addMessage(model, "操作失败，减少的考位数不能大于已剩余的数量！");
				return seatForm(exam, model);
			} else {
				exam.setTotalSeats(exam.getTotalSeats() - addSeats);
			}
		}
		examService.updateSeat(exam);
		return "redirect:"+Global.getAdminPath()+"/web/exam/?repage";
	}
	
	@RequiresPermissions("web:exam:view")
	@RequestMapping(value = "detail")
	public String detail(WebExamSignup webSig, HttpServletRequest request, HttpServletResponse response, Model model){
		WebExam webExam = new WebExam();
		webExam.setExamId(webSig.getExamId());
		List<WebExam> listCounts = examService.countSignupNum(webExam);
		List<Long> counts = new ArrayList<Long>();
		Long total = 0L;
		for(WebExam w : listCounts){
			if(w.getSignupNum() != null){
				total += w.getSignupNum();
			}
		}
		counts.add(total);
		Map<Integer, String> maps = new LinkedHashMap<Integer, String>();
		for(Integer i : Constants.ORDER_STATUS_TYPES.keySet()){
			Long num = 0L;
			for(WebExam we : listCounts){
				if(we.getExamSignupStatus().equals(i)){
					num = we.getSignupNum();
				}
			}
			counts.add(num);
			maps.put(i, Constants.ORDER_STATUS_TYPES.get(i) + "(" + num + ")");
		}
		WebExam exam = examService.get(webExam);
		exam.setExamSignupStatus(webExam.getExamSignupStatus());
		exam.setCourses(examService.selectCoursesByExamId(webExam));
		Page<WebExamSignup> signupPages = signupService.findPage(new Page<WebExamSignup>(request, response), webSig);
		model.addAttribute("maps", maps);
		model.addAttribute("page", signupPages);
		model.addAttribute("counts", counts);
		model.addAttribute("exam", exam);
		model.addAttribute("webSignup", webSig);
		return "web/exam/exam/detail";
	}
	
	@RequiresPermissions("web:exam:view")
	@RequestMapping(value = "daylist")
	public String daylist(WebExam webExam, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		String userId = user.getId();
		//先通过用户id查询是否有绑定考点
		WebExamPlace place = new WebExamPlace();
		place.setSysUserId(userId);
		//查询考点id列表
		List<WebExamPlace> placesList;
		//当为自有考试的时候,去查询考点信息
		if(webExam.getExamType() == null){
			webExam.setExamType(Constants.ExamType.self);
		}
		placesList = examPlaceService.findList(place);
		//如果id列表不为空
		if (placesList != null && placesList.size() > 0){
			webExam.getSqlMap().put("sysUserId",userId);
		}
		if(webExam.getExamFlag() == null){
			webExam.setExamFlag(Constants.ExamFlag.now);
		}

		//获取考点列表
		List<WebExamPlace> places = examService.selectPlaceByType(webExam);
		model.addAttribute("places", places);
		if (places != null && places.size() > 0) {
            webExam.setPlaces(places);
        }
		if(webExam==null||webExam.getExamStartTime()==null){
			webExam.setExamStartTime(new Date());			
		}
		webExam.setExamSignupStatus(30);
		
		List<WebExam> list = examService.getDayList(webExam);
		List<List<WebExamSignup>> ws= new ArrayList<List<WebExamSignup>>();
		for(WebExam w : list){			
			w.setCourses(examService.selectCoursesByExamId(w));
			WebExamSignup wes=new WebExamSignup();
			wes.setExamId(w.getExamId());
			wes.setExamSignupStatus(30);
			List<WebExamSignup> signups = signupService.findDayList(wes);
			ws.add(signups);
		}
		
//		Map<Integer, String> maps = new LinkedHashMap<Integer, String>();
//		for(Integer i : Constants.ORDER_STATUS_TYPES.keySet()){
//			Long num = 0l;
//			for(WebExam we : listCounts){
//				if(we.getExamSignupStatus().equals(i)){
//					num = we.getSignupNum();
//				}
//			}
//			counts.add(num);
//			maps.put(i, Constants.ORDER_STATUS_TYPES.get(i) + "(" + num + ")");
//		}

		//List<WebExamSignup> signups = signupService.findDayList(webSig);
		//model.addAttribute("maps", maps);
		model.addAttribute("examList", list);
		model.addAttribute("signups", ws);
		return "web/exam/exam/daylist";
	}
	
	//@RequiresPermissions("web:exam:del")
	@RequestMapping(value = "delete")
	public String delete(WebExam webExam, HttpServletRequest request, HttpServletResponse response, Model model){
		examService.deleteAll(webExam);
		addMessage(model, "删除成功！");
		return list(webExam, request, response, model);
	}
	
	@RequiresPermissions("web:exam:edit")
	@RequestMapping(value = "/getExamCourse")
	@ResponseBody
	public List<SmallCourse> getExamCourse(Long examId){
		WebExam we= new WebExam();
		we.setExamId(examId);
		List<SmallCourse> courses = examService.selectCoursesByExamId(we);
		return courses;
	}	
	
}