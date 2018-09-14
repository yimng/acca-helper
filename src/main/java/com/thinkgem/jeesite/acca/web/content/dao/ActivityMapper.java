package com.thinkgem.jeesite.acca.web.content.dao;

import com.thinkgem.jeesite.acca.web.content.entity.Activity;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import tk.mybatis.mapper.common.Mapper;
@MyBatisDao
public interface ActivityMapper extends Mapper<Activity> {
}