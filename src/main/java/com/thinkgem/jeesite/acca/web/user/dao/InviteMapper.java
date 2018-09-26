package com.thinkgem.jeesite.acca.web.user.dao;

import com.thinkgem.jeesite.acca.web.user.entity.Invite;
import com.thinkgem.jeesite.acca.web.user.entity.InviteRank;
import com.thinkgem.jeesite.common.persistence.MyMapper;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@MyBatisDao
public interface InviteMapper extends MyMapper<Invite> {
    public List<InviteRank> findInviteRank(@Param("start") Date start, @Param("end") Date end);
}