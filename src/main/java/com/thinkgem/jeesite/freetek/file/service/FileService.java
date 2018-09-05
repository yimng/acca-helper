package com.thinkgem.jeesite.freetek.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.freetek.file.dao.FileDao;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;



@Service
public class FileService {

	@Autowired
	private FileDao fileDao;
	
	/*public List<AttachmentFile> insertBatch(List<AttachmentFile> list){
		return attachmentFileDao.inertBatch(list);
		
		
	}*/
	
	public List<FileInfo> insertBatch(List<FileInfo> list){
		//SqlSession sqlSession = super.getSqlSession();
		for(FileInfo index:list){
			//sqlSession.insert("AttachmentFileMapper.insert",index);
			fileDao.insert(index);
			System.out.println("index:"+index);
		}
		
		//sqlSession.commit();
		System.out.println("list:"+list);
		return list;
		
	}
}
