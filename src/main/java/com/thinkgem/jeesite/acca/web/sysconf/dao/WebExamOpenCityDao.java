/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.dao;

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityPayRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityUserRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebExamOpenCity;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 城市管理DAO接口
 * @author Fan
 * @version 2016-08-24
 */
@MyBatisDao
public interface WebExamOpenCityDao extends CrudDao<WebExamOpenCity> {

	List<WebExamOpenCity> selectProvinces();

	List<WebExamOpenCity> selectCitys2();

	/**
	 * 通过城市的id删除关联的帐户信息
	 * @param id
     */
	void deletePayRelationById(Integer id);

	/**
	 * 保存关联信息
	 * @param relation
     */
	void saveRelation(WebCityPayRelation relation);

	/**
	 * 获取支付关联信息
	 * @param city
     */
	List<WebCityPayRelation> findPayRelationList(WebExamOpenCity city);
	
	/**
	 * 获取用户关联信息
	 * @param city
     */
	List<WebCityUserRelation> findUserRelationList(WebExamOpenCity city);
	
	/**
	 * 通过城市的id删除关联的帐户信息
	 * @param id
     */
	void deleteUserRelationById(Integer id);
	
	/**
	 * 保存关联信息
	 * @param relation
     */
	void saveUserRelation(WebCityUserRelation relation);
	/**
	 * 根据管理员用户id获得管理城市列表
	 * @param sysUserId
     */
	List<WebCityUserRelation> findCityIdListByUserId(String sysUserId);
}