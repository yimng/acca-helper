/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSignup;
import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * AppExamSignupDAO接口
 * @author Ivan
 * @version 2016-08-19
 */
@MyBatisDao
public interface AppExamSignupDao extends CrudDao<AppExamSignup> {
	
	void insertBatch(List<AppExamSignup> list);

	/**
	 * 获取最近两条符合条件的报考信息
	 * @return
     */
	List<AppExamSignup> findTipsList(AppExamSignup appExamSignup);
	public void updateStatusByOrderId(@Param("orderId")Long orderId,@Param("status")Integer status);
	
	//专门为获取支付配置信息而写的dao，根据订单id获取改订单下最近一门考试的报名信息
	public AppExamSignup getByOrderIdForPayConf(Long orderId);
	
}