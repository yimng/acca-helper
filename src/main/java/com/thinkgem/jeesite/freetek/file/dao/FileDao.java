/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.freetek.file.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * 单表生成DAO接口
 * @author Michael
 * @version 2016-07-13
 */
@MyBatisDao
public interface FileDao extends CrudDao<FileInfo> {

//	List<AttachmentFile> inertBatch(List<AttachmentFile> list);


	
	
}