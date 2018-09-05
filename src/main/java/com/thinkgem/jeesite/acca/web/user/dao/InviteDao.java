package com.thinkgem.jeesite.acca.web.user.dao;

import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MyBatisDao
public interface InviteDao extends CrudDao<Invite> {

    List<Invite> findListByTimeAndPhone(@Param("start") Date start, @Param("end") Date end, String phone);

    List<InviteRank> findInviteRank(@Param("start") Date start, @Param("end") Date end);
}
