/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.register.service;

import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.register.dao.WebAccaRegisterDao;
import com.thinkgem.jeesite.acca.web.register.entity.WebAccaRegister;
import com.thinkgem.jeesite.acca.web.user.dao.WebAccaUserDao;
import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 代注册Service
 * @author Young
 * @version 2016-08-17
 */
@Service
@Transactional(readOnly = true)
public class WebAccaRegisterService extends CrudService<WebAccaRegisterDao, WebAccaRegister> {

	@Autowired
	private WebAccaUserDao userDao;
	@Autowired
	private WebMsgSysService msgSysService;

	@Override
    public WebAccaRegister get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebAccaRegister> findList(WebAccaRegister webAccaRegister) {
		return super.findList(webAccaRegister);
	}
	
	@Override
    public Page<WebAccaRegister> findPage(Page<WebAccaRegister> page, WebAccaRegister webAccaRegister) {
		return super.findPage(page, webAccaRegister);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebAccaRegister webAccaRegister) {
		super.save(webAccaRegister);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebAccaRegister webAccaRegister) {
		super.delete(webAccaRegister);
	}

	@Transactional(readOnly = false)
	public void update(WebAccaRegister webAccaRegister) {
		dao.update(webAccaRegister);
	}

	/**
	 * 审核通过后,填写学员号和密码
	 * 并将信息保存到用户表中
	 * @param webAccaRegister
     */
	@Transactional(readOnly = false)
	public void updateRegisterPass(WebAccaRegister webAccaRegister) {
		dao.updateRegisterPass(webAccaRegister);
		//获取注册的详细信息
		webAccaRegister = dao.get(webAccaRegister);
		//将详细信息保存到用户表中
		WebAccaUser accaUser = userDao.get(String.valueOf(webAccaRegister.getAccaUserId()));
		accaUser.setRegisterName(webAccaRegister.getName());
		accaUser.setRegisterPhone(webAccaRegister.getPhone());
		accaUser.setRegisterCardNumber(webAccaRegister.getCardNumber());
		accaUser.setRegisterEmail(webAccaRegister.getEmail());
		accaUser.setRegisterWhiteColorImgId(webAccaRegister.getWhiteColorImgId());
		accaUser.setAccaRegisterName(webAccaRegister.getAccaRegisterName());
		accaUser.setAccaRegisterPassword(webAccaRegister.getAccaRegisterPassword());
		accaUser.setCardType(webAccaRegister.getCardType());
		accaUser.setIdentityType(webAccaRegister.getIdentityType());
		userDao.update(accaUser);
		//推送消息,并将消息保存到用户消息列表中
		String title = "注册成功";
		String content = "您已成功注册为ACCA学员，快去查看您的ACCA学员号和初始密码吧。";
		msgSysService.savePushToPersonal(title,content,webAccaRegister.getAccaUserId());
	}

	/**
	 * 审核
	 * @param webAccaRegister
     */
	@Transactional(readOnly = false)
	public void updateRegisterAudit(WebAccaRegister webAccaRegister) {
		Integer status = webAccaRegister.getStatus();
		//获取注册的详细信息
		WebAccaRegister oldAccaRegister = dao.get(webAccaRegister);
		//如果审核失败,则推送消息提醒
		if (status == 4){
			String title = "注册申请未通过审核";
			String content = "抱歉，您的注册申请未通过审核，快去查看原因并重新提交注册申请吧。";
			msgSysService.savePushToPersonal(title,content,oldAccaRegister.getAccaUserId());
		}
		dao.updateRegisterAudit(webAccaRegister);
	}


	public List<Map<String,Integer>> getStatusCount(WebAccaRegister registerType) {
		List<Map<String,Integer>> countMap = dao.getStatusCount(registerType);
		return countMap;
	}
}