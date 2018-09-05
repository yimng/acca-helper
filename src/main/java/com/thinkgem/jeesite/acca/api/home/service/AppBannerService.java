package com.thinkgem.jeesite.acca.api.home.service;

import com.thinkgem.jeesite.acca.api.article.dao.AppArticleDao;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;
import com.thinkgem.jeesite.acca.api.home.dao.AppBannerDao;
import com.thinkgem.jeesite.acca.api.home.entity.AppBanner;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.util.TextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页Service
 * @author Young
 * @version 2016-08-15
 */
@Service
@Transactional(readOnly = true)
public class AppBannerService extends CrudService<AppBannerDao, AppBanner> {

	@Autowired
	private AppBannerDao appBannerDao;
	
	@Autowired
	private AppArticleDao appArticleDao;


	/**
	 * 获取首页banner列表
	 * @param req
	 * @return
     */
	public BasePageResponse<AppBanner> getBannerList(BaseRequest req) {
		List<AppBanner> list = appBannerDao.getAppBannerList();
		if(list!=null && !list.isEmpty()){
			for(AppBanner index:list){
				int type = index.getType();
				if(type ==2){
					index.setHtmlPreview(TextUtils.getContentPreview(index.getHtmlContent(), 50));
				}else if(type==3){
					AppArticle article = new AppArticle();
					article.setArticleId(index.getArticleId());
					AppArticle entity = appArticleDao.get(article);
					if(entity!=null){
						index.setHtmlPreview(entity.getHtmlPreview());
					}
				}else{
					index.setHtmlPreview("ACCA小助手是国内首款专门为ACCA学生量身定制，集学习规划、报名考试、提醒监督、交流分享于一身的学习辅助工具");
				}
			}
		}
		
		return new BasePageResponse<AppBanner>(list);
	}
}