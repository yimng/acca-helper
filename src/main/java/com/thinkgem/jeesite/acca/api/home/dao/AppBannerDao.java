package com.thinkgem.jeesite.acca.api.home.dao;

import com.thinkgem.jeesite.acca.api.home.entity.AppBanner;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 首页BannerDAO接口
 * @author Young
 * @version 2016-08-15
 */
@MyBatisDao
public interface AppBannerDao extends CrudDao<AppBanner> {

    List<AppBanner> getAppBannerList();
}