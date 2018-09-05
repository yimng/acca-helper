/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import com.thinkgem.jeesite.acca.web.sysconf.dao.WebExamOpenCityDao;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityPayRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityUserRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebExamOpenCity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 城市管理Service
 * @author Fan
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class WebExamOpenCityService extends CrudService<WebExamOpenCityDao, WebExamOpenCity> {

	@Override
    public WebExamOpenCity get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebExamOpenCity> findList(WebExamOpenCity webExamOpenCity) {
		return super.findList(webExamOpenCity);
	}
	
	@Override
    public Page<WebExamOpenCity> findPage(Page<WebExamOpenCity> page, WebExamOpenCity webExamOpenCity) {
		return super.findPage(page, webExamOpenCity);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebExamOpenCity webExamOpenCity) {
		super.save(webExamOpenCity);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebExamOpenCity webExamOpenCity) {
		super.delete(webExamOpenCity);
	}

	public List<WebExamOpenCity> selectProvinces() {
		// TODO Auto-generated method stub
		return dao.selectProvinces();
	}

	public List<WebExamOpenCity> selectCitys2() {
		// TODO Auto-generated method stub
		return dao.selectCitys2();
	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(WebExamOpenCity webExamOpenCity, String accountIds,String userIds) {
		String[] accountIdArr;
		String[] userIdArr;
		if (StringUtils.isEmpty(webExamOpenCity.getId())){
			super.save(webExamOpenCity);
		} else {
			dao.update(webExamOpenCity);
		}
		//保存城市与帐户的关联信息
		Integer cityId = webExamOpenCity.getCityId();
		//先通过城市的id删除掉之前的关联信息
		dao.deletePayRelationById(cityId);
		if (StringUtils.isNotEmpty(accountIds)){
			accountIdArr = accountIds.split(",");
			//遍历插入
			for (String accountId:accountIdArr){
				WebCityPayRelation relation = new WebCityPayRelation();
				relation.setCityId(cityId);
				relation.setPayConfId(Integer.valueOf(accountId));
				dao.saveRelation(relation);
			}			
		}
		dao.deleteUserRelationById(cityId);
		if (StringUtils.isNotEmpty(userIds)){
			userIdArr = userIds.split(",");
			//遍历插入
			for (String userId:userIdArr){
				if (StringUtils.isNotEmpty(userId)){
					WebCityUserRelation relation = new WebCityUserRelation();
					relation.setCityId(cityId);
					relation.setSysUserId(userId);
					dao.saveUserRelation(relation);
				}
			}			
		}
	}

	/**
	 * 获取支付关联列表
	 * @param city
     */
	public List<WebCityPayRelation> findPayRelationList(WebExamOpenCity city) {
		return dao.findPayRelationList(city);
	}

	public List<WebCityUserRelation> findUserRelationList(WebExamOpenCity city) {		
		return dao.findUserRelationList(city);
	}
	/**
	 * 根据管理员用户id获得管理城市列表
	 * @param sysUserId
     */
	public List<WebCityUserRelation> findCityIdListByUserId(String sysUserId) {
		return dao.findCityIdListByUserId(sysUserId) ;
	}
}