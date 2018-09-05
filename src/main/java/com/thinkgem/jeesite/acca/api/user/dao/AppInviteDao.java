package com.thinkgem.jeesite.acca.api.user.dao;

import com.thinkgem.jeesite.acca.api.user.entity.AppInvite;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface AppInviteDao extends CrudDao<AppInvite> {
}
