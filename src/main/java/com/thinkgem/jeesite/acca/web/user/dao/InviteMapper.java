package com.thinkgem.jeesite.acca.web.user.dao;

import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tk.mybatis.mapper.common.Mapper;
@MyBatisDao
public interface InviteMapper extends Mapper<Invite> {
}