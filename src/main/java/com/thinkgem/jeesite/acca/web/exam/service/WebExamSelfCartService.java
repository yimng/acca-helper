package com.thinkgem.jeesite.acca.web.exam.service;

import com.thinkgem.jeesite.acca.web.exam.dao.WebExamSelfCartDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSelfCart;
import com.thinkgem.jeesite.acca.web.user.dao.UserCouponMapper;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.AttributeOverride;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class WebExamSelfCartService extends CrudService<WebExamSelfCartDao, WebExamSelfCart> {

	@Autowired
	private UserCouponMapper userCouponMapper;
	
	@Transactional(readOnly = false)
	public void cancelExamSelfCart() {
		logger.info("cancelExamSelfCart start!!!!!!!!!!!!!");
		Date expireDate = TimeUtils.getNextDay(-1);
		/*List<WebExamSelfCart> list = dao.getListByExpireTime(expireDate);
		logger.info("cancelExamSelfCart:"+list);
		if(list==null && list.isEmpty()){
			return;
		}
		for(WebExamSelfCart index:list){

		}
		*/

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 3);
		dao.deleteByExpireTime(expireDate);
		logger.info("cancelExamSelfCart end!!!!!!!!!!!!!");
	}

	@Transactional(readOnly = false)
	public void cancelCoupon() {
		logger.info("cancelCoupon start!!!!!!!!!!!!!");

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -3);
		userCouponMapper.deleteByExpireTime(now.getTime());
		logger.info("cancelCoupon end!!!!!!!!!!!!!");
	}
	
	

}
