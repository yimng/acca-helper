package com.thinkgem.jeesite.acca.api.user.dao;

import com.thinkgem.jeesite.acca.api.user.entity.AppInvite;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tk.mybatis.mapper.common.Mapper;
@MyBatisDao
public interface AppInviteMapper extends Mapper<AppInvite> {
}