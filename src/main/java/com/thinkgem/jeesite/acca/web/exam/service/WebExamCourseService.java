/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.service;

import com.thinkgem.jeesite.acca.web.exam.dao.WebExamCourseDao;
import com.thinkgem.jeesite.acca.web.exam.dao.WebExamCourseVersionDao;
import com.thinkgem.jeesite.acca.web.exam.dao.WebExamVersionDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourseVersion;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamVersion;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试Service
 * @author Fan
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebExamCourseService extends CrudService<WebExamCourseDao, WebExamCourse> {

	@Autowired
	private WebExamCourseVersionDao examCourseVersionDao;
	@Autowired
	private WebExamVersionDao examVersionDao;

	@Override
    public WebExamCourse get(String id) {
		return super.get(id);
	}

	@Override
    public List<WebExamCourse> findList(WebExamCourse webExamCourse) {
		return super.findList(webExamCourse);
	}

	@Override
    public Page<WebExamCourse> findPage(Page<WebExamCourse> page, WebExamCourse webExamCourse) {
		return super.findPage(page, webExamCourse);
	}

	/**
	 * 保存考试信息
	 * @param webExamCourse
	 * @param versions
     */
	@Transactional(readOnly = false)
	public String save(WebExamCourse webExamCourse, String versions) {
		String message = "";
		if (webExamCourse.getExamCourseId() == null){
			super.save(webExamCourse);
			message = "保存考试科目成功";
		} else {
			dao.update(webExamCourse);
			message = "修改考试科目成功";
		}
		Long examCourseId = webExamCourse.getExamCourseId();
		WebExamCourseVersion condition = new WebExamCourseVersion();
		condition.setExamCourseId(examCourseId);
		//先根据课程id删除关联信息
		examCourseVersionDao.delete(condition);
		if (StringUtils.isNotEmpty(versions)){
			//保存版本关联信息
			String[] versionIdArr = versions.split(",");
			for (String versionId:versionIdArr){
				WebExamCourseVersion examCourseVersion = new WebExamCourseVersion();
				examCourseVersion.setExamCourseId(examCourseId);
				examCourseVersion.setExamVersionId(Long.valueOf(versionId));
				examCourseVersionDao.insert(examCourseVersion);
			}
		}
		return message;
	}

	@Override
    @Transactional(readOnly = false)
	public void save(WebExamCourse webExamCourse) {
		if (webExamCourse.getExamCourseId() != null){
			dao.update(webExamCourse);
		} else {
			super.save(webExamCourse);
		}
	}

	@Override
    @Transactional(readOnly = false)
	public void delete(WebExamCourse webExamCourse) {
		super.delete(webExamCourse);
	}

	public List<WebExamCourse> findMyList(WebExamCourse webExamCourse) {
		// TODO Auto-generated method stub
		return dao.findMyList(webExamCourse);
	}

	/**
	 * 通过科目id获取考试版本信息
	 * @param courseId
	 * @return
     */
	public List<WebExamVersion> getExamCourseVersion(Long courseId){
		List<WebExamVersion> versionList = new ArrayList<WebExamVersion>();
		//先通过科目id获取关联表
		WebExamCourseVersion condition = new WebExamCourseVersion();
		condition.setExamCourseId(courseId);
		List<WebExamCourseVersion> courseVersionList = examCourseVersionDao.findList(condition);
		//遍历考试版本列表
		for (WebExamCourseVersion version:courseVersionList){
			WebExamVersion vCondition = new WebExamVersion();
			vCondition.setExamVersionId(version.getExamVersionId());
			//获取考试版本信息
			WebExamVersion examVersion = examVersionDao.get(vCondition);
			versionList.add(examVersion);
		}
		return versionList;
	}

	/**
	 * 添加考试版本信息
	 * @param versionName
	 * @return
     */
	@Transactional(readOnly = false)
	public Long addExamVersion(String versionName) {
		WebExamVersion examVersion = new WebExamVersion();
		examVersion.setExamVersionName(versionName);
		examVersionDao.insert(examVersion);
		return examVersion.getExamVersionId();
	}

}