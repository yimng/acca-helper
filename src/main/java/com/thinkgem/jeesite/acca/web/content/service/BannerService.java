/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.BannerDao;
import com.thinkgem.jeesite.acca.web.content.entity.Banner;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * banner设置Service
 * @author Young
 * @version 2016-08-22
 */
@Service
@Transactional(readOnly = true)
public class BannerService extends CrudService<BannerDao, Banner> {

	@Override
	public Banner get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<Banner> findList(Banner banner) {
		return super.findList(banner);
	}
	
	@Override
	public Page<Banner> findPage(Page<Banner> page, Banner banner) {
		return super.findPage(page, banner);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(Banner banner) {
		super.save(banner);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(Banner banner) {
		super.delete(banner);
	}

	/**
	 * 保存或更新banner信息
	 * @param banner
	 * @return
     */
	@Transactional(readOnly = false)
	public String saveOrUpdate(Banner banner) {
		String message = "";
		String htmlContent = banner.getHtmlContent();
		//对保存的HTML内容进行解码
		if (StringUtils.isNotEmpty(htmlContent)) {
            banner.setHtmlContent(Encodes.unescapeHtml(htmlContent));
        }
		if (banner.getBannerId() == null) {
			//进行保存
			super.save(banner);

			Integer type = banner.getType();
			String projectBaseUrl = Global.getProjectBaseUrl();
			//判断type为链接1,还是富文本2,还是有资有料3.如果为富文本,有资有料,则生成新的url保存
			if (type == 2){
				String linkUrl = projectBaseUrl + "/api/home/html?bannerId=" + banner.getBannerId();
				banner.setLinkUrl(linkUrl);
			} else if (type == 3){
				String linkUrl = projectBaseUrl + "/api/article/detail?articleId=" + banner.getArticleId();
				banner.setLinkUrl(linkUrl);
			}
			//生成链接后,更新链接上去
			dao.update(banner);
			message = "保存Banner成功!";
		} else {
			Integer type = banner.getType();
			String projectBaseUrl = Global.getProjectBaseUrl();
			//判断type为链接1,还是富文本2,还是有资有料3.如果为富文本,有资有料,则生成新的url保存
			if (type == 2){
				String linkUrl = projectBaseUrl + "/api/home/html?bannerId=" + banner.getBannerId();
				banner.setLinkUrl(linkUrl);
			} else if (type == 3){
				String linkUrl = projectBaseUrl + "/api/article/detail?articleId=" + banner.getArticleId();
				banner.setLinkUrl(linkUrl);
			}
			dao.update(banner);
			message = "修改Banner设置成功!";
		}
		return message;
	}
}