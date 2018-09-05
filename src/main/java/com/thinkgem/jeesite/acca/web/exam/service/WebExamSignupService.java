package com.thinkgem.jeesite.acca.web.exam.service;

import com.thinkgem.jeesite.acca.api.home.dao.AppTipsDao;
import com.thinkgem.jeesite.acca.api.home.entity.AppTips;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.exam.dao.WebExamSignupDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSignup;
import com.thinkgem.jeesite.acca.web.exam.entity.WebSignup;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WebExamSignupService extends CrudService<WebExamSignupDao, WebExamSignup> {

    @Autowired
    private WebMsgSysService msgSysService;
    @Autowired
    private WebExamSignupDao signupDao;
    @Autowired
    private AppTipsDao tipsDao;

    /**
     * 推送考试提醒和日历提醒
     */
    @Transactional(readOnly = false)
    public void updateSignupOrTips() {
        //获取今天的日期,然后,作为查询条件传入,获取今天需要推送的列表
        Date date = new Date();
        String pushTime = DateTimeUtils.convertDate2String(date, "yyyy-MM-dd");
        List<AppTips> tipsList = tipsDao.findListByDay(pushTime);

        logger.info("pushSignupOrTips start!!!!!!!!!!!!!");
        if (tipsList != null && tipsList.size() > 0){
            //对考试列表进行遍历,并进行消息推送
            for (AppTips tips:tipsList){
                String title = tips.getTipTitle();
                String content = tips.getPushContent();
                Long userId = tips.getAccaUserId();
                //进行推送
                msgSysService.savePushToPersonal(title,content,userId);
            }
        }
        logger.info("pushSignupOrTips start!!!!!!!!!!!!!");
    }

	public List<WebSignup> findListByOrder(WebSignup ws) {
		// TODO Auto-generated method stub
		return signupDao.findListByOrder(ws);
	}
	
	@Transactional(readOnly = false)
	public void changeExam(WebExamSignup wes) {
		// TODO Auto-generated method stub
		if(wes.getExamSignupId()!=null){
			signupDao.changeExam(wes);
		}
		
	}

	public List<WebExamSignup> findDayList(WebExamSignup webSig) {
		// TODO Auto-generated method stub
		return signupDao.findDayList(webSig);
	}
}
