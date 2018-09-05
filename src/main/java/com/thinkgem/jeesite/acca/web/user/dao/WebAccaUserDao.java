/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.dao;

import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * accaUser模块DAO接口
 * @author Ivan
 * @version 2016-08-17
 */
@MyBatisDao
public interface WebAccaUserDao extends CrudDao<WebAccaUser> {

	List<WebAccaUser> findMyList();

	void freeze(WebAccaUser webAccaUser);

	void updateType(WebAccaUser webAccaUser);

	List<WebAccaUser> getDetailList(Map<String, Object> params);

	void updateRegister(WebAccaUser webAccaUser);

	/**
	 * 根据id字符串获取用户列表
	 * @param userIds
	 * @return
     */
	List<WebAccaUser> findPreList(@Param(value = "userIds") List<Long> userIds);

	void updateIszbg(WebAccaUser webAccaUser);
}