/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.acca.api.plan.dao.AppUserLearningPlanDao;
import com.thinkgem.jeesite.acca.constant.WebConstant;
import com.thinkgem.jeesite.acca.web.content.dao.WebArticleDao;
import com.thinkgem.jeesite.acca.web.content.dao.WebMsgPushLogDao;
import com.thinkgem.jeesite.acca.web.content.dao.WebMsgSysDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticle;
import com.thinkgem.jeesite.acca.web.content.entity.WebMsgPushLog;
import com.thinkgem.jeesite.acca.web.content.entity.WebMsgSys;
import com.thinkgem.jeesite.acca.web.exam.dao.WebExamSignupDao;
import com.thinkgem.jeesite.acca.web.user.dao.WebAccaUserDao;
import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.sms.SmsUtils;
import com.thinkgem.jeesite.freetek.util.jpush.JpushEntity;
import com.thinkgem.jeesite.freetek.util.jpush.JpushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统消息Service
 * @author Young
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class WebMsgSysService extends CrudService<WebMsgSysDao, WebMsgSys> {

	@Autowired
	private WebMsgPushLogDao msgPushLogDao;
	@Autowired
	private WebAccaUserDao userDao;
	@Autowired
	private WebArticleDao articleDao;
	@Autowired
	private WebExamSignupDao examSignupDao;

	@Override
    public WebMsgSys get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebMsgSys> findList(WebMsgSys webMsgSys) {
		return super.findList(webMsgSys);
	}
	
	@Override
    public Page<WebMsgSys> findPage(Page<WebMsgSys> page, WebMsgSys webMsgSys) {
		return super.findPage(page, webMsgSys);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebMsgSys webMsgSys) {
		super.save(webMsgSys);
	}

	/**
	 * 进行消息推送和再次推送
	 * @param webMsgSys
	 * @return
     */
	@Transactional(readOnly = false)
	public String saveOrUpdate(WebMsgSys webMsgSys) {
		webMsgSys.setMsgContent(Encodes.unescapeHtml(webMsgSys.getMsgContent()));
		webMsgSys.setMsgTitle(Encodes.unescapeHtml(webMsgSys.getMsgTitle()));
		String message = "";
		if (webMsgSys.getMsgId() == null){
			//先将消息保存到后台系统表中
			super.save(webMsgSys);
			this.saveMsgLogAndPushMsg(webMsgSys);
			message = "消息推送成功";
		} else {
			//通过传进来的id,获取消息内容,进行推送并保存
			webMsgSys = dao.get(webMsgSys);
			this.saveMsgLogAndPushMsg(webMsgSys);
			message = "消息再次推送成功";
		}
		return message;
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebMsgSys webMsgSys) {
		super.delete(webMsgSys);
	}

	/**
	 * 推送个人消息,并保存
	 * @param title
	 * @param content
	 * @param userId
     */
	@Transactional(readOnly = false)
	public void savePushToPersonal(String title,String content,Long userId){
		content = Encodes.unescapeHtml(content);
		title = Encodes.unescapeHtml(title);
		//定义极光推送实体
		JpushEntity jpushEntity = new JpushEntity();
		jpushEntity.setTitle(title);
		jpushEntity.setAlert(content);
		List<String> pushIdList =  new ArrayList<String>();
		pushIdList.add("appuser" + userId);
		jpushEntity.setAlias(pushIdList);
		JpushUtil.pushByTag(jpushEntity);
		//保存推送的信息
		WebMsgSys webMsgSys = new WebMsgSys();
		webMsgSys.setMsgTitle(title);
		webMsgSys.setMsgContent(content);
		webMsgSys.setJumpType(0);
		List<Long> userIdList = new ArrayList<Long>();
		userIdList.add(userId);
		this.savePushLog(userIdList,webMsgSys,null);
	}

	/**
	 * 进行消息推送,并保存消息
	 * @param webMsgSys
     */
	private void saveMsgLogAndPushMsg(WebMsgSys webMsgSys){
		//获取消息推送的人群,是所有的用户,还是待考试用户
		Integer pushPeople = webMsgSys.getPushPeople();
		String articleUrl = "";
		//对是否有选择文章进行处理
		Long articleId = webMsgSys.getArticleId();
		Map<String,String> extraMap = new HashMap<String, String>();
		if (articleId != null){
			WebArticle article = articleDao.get(String.valueOf(articleId));
			articleUrl = article.getLinkUrl();
			if (StringUtils.isNotEmpty(articleUrl)){
				extraMap.put("url",articleUrl);
				extraMap.put("id",articleId+"");
			}
		}
		//定义推送的用户id列表
		List<Long> userIdList = new ArrayList<Long>();
		//定义极光推送实体
		JpushEntity jpushEntity = new JpushEntity();
		jpushEntity.setTitle(webMsgSys.getMsgTitle());
		jpushEntity.setAlert(webMsgSys.getMsgContent());
		jpushEntity.setExtra(extraMap);
		//如果是所有人群,则调用推送给所有用户的方法,并获取所有的用户,将消息保存到用户的消息表中
		if (pushPeople == WebConstant.MSG_PUSH_PEOPLE_ALL){
			//获取所有的用户
			List<WebAccaUser> userList = userDao.findList(new WebAccaUser());
			//获取到所有用户的id
			for (WebAccaUser user:userList){
				userIdList.add(user.getAccaUserId());
			}
			//进行全员推送
			JpushUtil.pushAll(jpushEntity);
		} else {
			//通过选取的科目,获取考试关联的用户的id列表
			userIdList = examSignupDao.findListByCourseIds(webMsgSys.getExamCourse());
			List<String> pushIdList =  new ArrayList<String>();
			if (userIdList != null){
				for(Long userId:userIdList){
					pushIdList.add("appuser" + userId);
				}
			}
			//并按考试用户进行推送
			jpushEntity.setAlias(pushIdList);
			JpushUtil.pushByTag(jpushEntity);
		}
		//保存推送给用户的信息
		this.savePushLog(userIdList,webMsgSys,extraMap);
	}

	/**
	 * 保存推送的用户信息
	 * @param userIdList
	 * @param webMsgSys
     */
	private void savePushLog(List<Long> userIdList,WebMsgSys webMsgSys,Map<String,String> extraMap){
		String msgTitle = webMsgSys.getMsgTitle();
		String msgContent = webMsgSys.getMsgContent();
		Integer jumpType = webMsgSys.getJumpType();
		Long articleId = webMsgSys.getArticleId();

		List<WebMsgPushLog> logList = new ArrayList<WebMsgPushLog>();
		//将推送的消息保存到用户消息表中
		for (Long userId:userIdList){
			WebMsgPushLog msgPushLog = new WebMsgPushLog();
			msgPushLog.setReceiveId(userId);
			msgPushLog.setMsgTitle(msgTitle);
			msgPushLog.setMsgContent(msgContent);
			if (articleId != null && extraMap != null){
				msgPushLog.setExtra(JSONUtils.toJSONString(extraMap));
			}
			//消息类型现在都默认为个人的
			msgPushLog.setMsgType(0);
			//消息分组,0默认组(不跳转) ,1 有资有料文章
			msgPushLog.setMsgGroup(jumpType);
			msgPushLog.setPushTime(new Date());
			//现在是不管是否成功,都会存入消息表,所以,都默认为推送成功
			msgPushLog.setPushStatus(1);
			msgPushLog.setReadStatus(WebConstant.MSG_PUSH_READ_NO);
			logList.add(msgPushLog);
		}
		msgPushLogDao.insertBatch(logList);
	}

	@Autowired
	private AppUserLearningPlanDao planDao;

	/**
	 * 给以前的用户推送优惠码,只在升级前使用一次
	 */
	@Transactional(readOnly = false)
	public void pushAll() {
		List<Long> userIds = planDao.getPreUsers();
		List<WebAccaUser> userList = userDao.findPreList(userIds);
		//对用户id进行遍历,进行推送和发送提醒短信
		for (WebAccaUser appAccaUser:userList){
			String phone = appAccaUser.getPhone();
			String title = "已成功完成任务";
			String content = "同学您好，您已经成功完成了我们的任务，现在可以领取我们为您准备的精品课程了! 领取地址 http://www.china-ife.com/S?UserKey=lucky2016";
			this.savePushToPersonal(title,content,appAccaUser.getAccaUserId());
			String msg = "同学您好，您已经成功完成了我们的任务，现在可以领取我们为您准备的精品课程了! 点击领取 http://www.china-ife.com/S?UserKey=lucky2016";
			//发送提醒短信
			SmsUtils.sendSms2Vcode(phone,msg);
		}
	}
}