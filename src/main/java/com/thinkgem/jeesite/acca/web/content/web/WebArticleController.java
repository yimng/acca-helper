/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.web;

import com.thinkgem.jeesite.acca.constant.WebConstant;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticle;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleCategory;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleSubjectRelation;
import com.thinkgem.jeesite.acca.web.content.entity.WebTeacher;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleCategoryService;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleService;
import com.thinkgem.jeesite.acca.web.content.service.WebArticleSubjectRelationService;
import com.thinkgem.jeesite.acca.web.content.service.WebTeacherService;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamCourseService;
import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.acca.web.user.service.WebAccaUserService;
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
 * 文章Controller
 * @author Young
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/article")
public class WebArticleController extends BaseController {
	@Autowired
	private WebArticleService articleService;
	@Autowired
	private WebArticleCategoryService articleCategoryService;
	@Autowired
	private WebArticleSubjectRelationService articleSubjectRelationService;
	@Autowired
	private WebExamCourseService examCourseService;
	@Autowired
	private WebAccaUserService userService;
	@Autowired
	private WebTeacherService teacherService;

	@ModelAttribute
	public WebArticle get(@RequestParam(required=false) String id) {
		WebArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = articleService.get(id);
		}
		if (entity == null){
			entity = new WebArticle();
		}
		return entity;
	}

	/**
	 * 文章列表
	 * @param webArticle
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("article:webArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebArticle webArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		//获取文章列表
		Page<WebArticle> page = articleService.findPage(new Page<WebArticle>(request, response), webArticle);
		//获取文章分类,进行跳转判断
		Integer type = webArticle.getType();
		if (type == WebConstant.ARTICLE_TYPE_COMMON || type == WebConstant.ARTICLE_TYPE_OPEN){
			//获取文章分类列表
			List<WebArticleCategory> categoryList = articleCategoryService.findList(new WebArticleCategory());
			List<WebArticle> relationCourses = getRelationCourses(page);
			model.addAttribute("page", page);
			model.addAttribute("categoryList",categoryList);
			return "web/content/article/webArticleList";
		} else if (type == WebConstant.ARTICLE_TYPE_LEARNER){
			model.addAttribute("page", page);
			return "web/content/learner/learnerArticleList";
		} else if (type == WebConstant.ARTICLE_TYPE_H5){
			model.addAttribute("page", page);
			return "web/content/H5/h5ArticleList";
		} else {
			model.addAttribute("page", page);
			return "web/content/teacher/teacherArticleList";
		}
	}

	@RequiresPermissions("article:webArticle:view")
	@RequestMapping(value = "form")
	public String form(WebArticle webArticle, Model model) {
		//获取文章类型,通过类型判断修改的页面,以及需要传递的数据
		Integer type = webArticle.getType();
		Long articleId = webArticle.getArticleId();
		if (articleId != null){
			//获取文章内容
			webArticle = articleService.get(webArticle);
		}
		model.addAttribute("webArticle", webArticle);
		//当文章类型为有资有料文章和公开课文章的时候,需要获取到科目列表,关联的文章科目以及文章分类
		if (type == WebConstant.ARTICLE_TYPE_COMMON || type == WebConstant.ARTICLE_TYPE_OPEN){
			//获取科目列表
			List<WebExamCourse> examCourses = examCourseService.findList(new WebExamCourse());
			//根据文章的id,获取关联的科目列表以及文章内容
			List<Long> courseIdList = new ArrayList<Long>();
			if (articleId != null){
				//获取关联科目
				WebArticleSubjectRelation condition = new WebArticleSubjectRelation();
				condition.setArticleId(articleId);
				List<WebArticleSubjectRelation> list = articleSubjectRelationService.findList(condition);
				for (WebArticleSubjectRelation relation:list){
					courseIdList.add(relation.getSubjectId());
				}
			}
			//获取文章分类列表
			List<WebArticleCategory> categoryList = articleCategoryService.findList(new WebArticleCategory());
			model.addAttribute("examCourses",examCourses);
			model.addAttribute("categoryList",categoryList);
			model.addAttribute("courseIdList",courseIdList);
			return "web/content/article/webArticleForm";
		} else if (type == WebConstant.ARTICLE_TYPE_LEARNER){
			//当为学习达人分享文章时,获取用户列表
			List<WebAccaUser> learnShareUserList = userService.findMyList();
			model.addAttribute("learnShareUserList",learnShareUserList);
			return "web/content/learner/learnerArticleForm";
		} else if (type == WebConstant.ARTICLE_TYPE_H5){
			//当为学习达人分享文章时,获取用户列表
			//List<WebAccaUser> learnShareUserList = userService.findMyList();
			return "web/content/H5/h5ArticleForm";
		}else {
			//党委名师指导时,获取教师的列表
			List<WebTeacher> teacherList = teacherService.findList(new WebTeacher());
			model.addAttribute("teacherList",teacherList);
			return "web/content/teacher/teacherArticleForm";
		}
	}

	/**
	 * 保存文章信息
	 * @param webArticle
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("article:webArticle:edit")
	@RequestMapping(value = "save")
	public String save(WebArticle webArticle,String courseIdList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webArticle)){
			return form(webArticle, model);
		}
		//获取文章类型
		Integer type = webArticle.getType();
		if (type == WebConstant.ARTICLE_TYPE_COMMON || type == WebConstant.ARTICLE_TYPE_OPEN){
			//查询保存文章的类型是普通文章还是公开课
			if (webArticle.getHtmlImageId() == null){
				addMessage(model, "封面图片不能为空!");
				return form(webArticle, model);
			}
			WebArticleCategory webArticleCategory = articleCategoryService.get(String.valueOf(webArticle.getArticleCategoryId()));
			if (webArticleCategory.getType() == WebConstant.ARTICLECATEGORY_TYPE_HTML){
				//设置为有资有料普通文章
				webArticle.setType(WebConstant.ARTICLE_TYPE_COMMON);
			} else if (webArticleCategory.getType() == WebConstant.ARTICLECATEGORY_TYPE_OPEN){
				//设置为公开课文章
				webArticle.setType(WebConstant.ARTICLE_TYPE_OPEN);
				//公开课的时候,判断图片是否为空,如果为空,则提示
				if (webArticle.getCourseImageId() == null){
					addMessage(model, "图片不能为空!");
					return form(webArticle, model);
				}
			}
		} else if (type == WebConstant.ARTICLE_TYPE_TEACHER){
			WebTeacher teacher = teacherService.get(String.valueOf(webArticle.getTeacherId()));
			webArticle.setCourseTeacher(teacher.getChName());
		}

		if (webArticle.getSortNum() == null) {
			webArticle.setSortNum(255);
		}
		//判断文章的id是否为空,如果为空,则进行保存
		if (webArticle.getArticleId() == null){
			articleService.saveArticle(webArticle,courseIdList);
			addMessage(redirectAttributes, "保存文章成功");
		} else {
			articleService.updateArticle(webArticle,courseIdList);
			addMessage(redirectAttributes, "修改文章成功");
		}
		return "redirect:"+Global.getAdminPath()+"/article/list?type="+ type;
	}

	@RequiresPermissions("article:webArticle:edit")
	@RequestMapping(value = "delete")
	public String delete(WebArticle webArticle, RedirectAttributes redirectAttributes) {
		articleService.delete(webArticle);
		addMessage(redirectAttributes, "删除文章成功");
		return "redirect:"+Global.getAdminPath()+"/article/list?type="+webArticle.getType();
	}

	/**
	 * 获取课程类型
	 * @param model
	 * @param articleCategoryId
	 * @return
	 */
	@RequiresPermissions("article:webArticle:view")
	@RequestMapping(value = "/getCourseType")
	@ResponseBody
	public Integer getCourseType(Model model,String articleCategoryId) {
		WebArticleCategory webArticleCategory = articleCategoryService.get(articleCategoryId);
		model.addAttribute("type",webArticleCategory.getType());
		return webArticleCategory.getType();
	}

	private List<WebArticle> getRelationCourses(Page<WebArticle> page){
		//获取文章的列表
		List<WebArticle> webArticles = page.getList();
		//对列表进行遍历,获取关联的科目
		if (webArticles != null && webArticles.size() > 0){
			for (WebArticle article:webArticles){
				Long articleId = article.getArticleId();
				//通过id获取关联列表
				WebArticleSubjectRelation relation = new WebArticleSubjectRelation();
				relation.setArticleId(articleId);
				List<WebArticleSubjectRelation> subjectRelations = articleSubjectRelationService.findList(relation);
				//遍历关联列表,获取科目信息
				String relationCourse = "";
				if (subjectRelations != null && subjectRelations.size() > 0){
					for (WebArticleSubjectRelation subjectRelation:subjectRelations){
						Long subjectId = subjectRelation.getSubjectId();
						if (subjectId == -1){
							relationCourse += "前导" + "/";
						} else {
							WebExamCourse c = new WebExamCourse();
							c.setExamCourseId(subjectId);
							relationCourse += subjectRelation.getCourse().getCourse() + "/";
						}
					}
					relationCourse = relationCourse.substring(0,relationCourse.length() - 1);
					article.setRelationCourse(relationCourse);
				}
			}
		}
		return webArticles;
	}
	
	
	
	
	
	public static void main(String args[]){		
//		WebArticleService articleService =new WebArticleService();
//		String t=articleService.getToken();
//		//System.out.println(articleService.getCaicuiUser(t,"18802258742"));
//		String regActiveID = "0";
//		String regActiveType = "4";
//		String nickName = "ACCAHelper13910880907";
//		String password = "880907";
//		String email = "880907@ACCAHelper.com";
//		String name = "小助手";
//		String gender = "1";
//		String areaPath = "北京";
//		String address = "-";
//		String mobile = "13910880907";
//		String identityType = "2";
//		
//		System.out.println(articleService.addCaicuiUser(t,regActiveID,regActiveType,nickName,password,email,
//				name,gender,areaPath,address,mobile,identityType));
//		//System.out.println(articleService.getUserCoupon("13910880907"));	
//		//System.out.println(articleService.AddCoupon(t, "18802258742", "8a22ecb558b34daf0158b91825470012"));
	}
}