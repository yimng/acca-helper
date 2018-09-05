/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.dao;

import com.thinkgem.jeesite.acca.web.exam.entity.WebExamPlace;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 考点类DAO接口
 * @author Michael
 * @version 2016-08-28
 */
@MyBatisDao
public interface WebExamPlaceDao extends CrudDao<WebExamPlace> {

    /**
     * 获取考点id列表
     * @param place
     * @return
     */
    List<Long> findPlacesId(WebExamPlace place);
}