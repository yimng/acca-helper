package com.thinkgem.jeesite.freetek.file.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import com.thinkgem.jeesite.freetek.file.entity.RespConstants;
import com.thinkgem.jeesite.freetek.file.entity.UploadFileResp;
import com.thinkgem.jeesite.freetek.file.service.FileService;
import com.thinkgem.jeesite.freetek.util.ApkUtils;
import com.thinkgem.jeesite.freetek.util.FileUtils;
import com.thinkgem.jeesite.freetek.util.ImageCompressUtil;
import com.thinkgem.jeesite.freetek.util.TextUtils;


@Controller
@RequestMapping("${apiPath}/files")
public class FileController { 
	
	private static Logger logger = Logger.getLogger(FileController.class);
	
	//@Value("#{configProperties['upload.file.base.dir']}")
	@Value("${upload.file.base.dir}")
	private String uploadFilePath = ""; // 上传文件的目录
	
	//@Value("#{configProperties['upload.file.base.url']}")
	@Value("${upload.file.base.url}")
	private String uploadUrlPath = ""; // 上传文件的url目录   
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value="/uploadFiles.do",method=RequestMethod.POST)  
	public @ResponseBody UploadFileResp uploadFiles(@RequestParam MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(files==null || files.length<1){
			return new UploadFileResp(RespConstants.GLOBAL_OTHER, "文件未上传");
		}
		
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		
		for(MultipartFile fileItem:files ){
			
			FileInfo atFile = saveFile(fileItem);
			if(atFile==null){
				return new UploadFileResp(RespConstants.GLOBAL_OTHER);
			}
			fileList.add(atFile);
			
		}
		logger.info("fileList:"+fileList);
		fileService.insertBatch(fileList);
		
		return new UploadFileResp(RespConstants.GLOBAL_SUCCESS,fileList);
	}
	
	
	
	
	
	public FileInfo saveFile(MultipartFile file) throws IllegalStateException, IOException{

		String oldFileName = file.getOriginalFilename();
		String fileExtName = FileUtils.getFileExtName(oldFileName);
		String contentType = file.getContentType();
		
		if (StringUtils.isEmpty(fileExtName)) {
			return null;
		}
		
		String newName= "" + System.currentTimeMillis()+ TextUtils.getNbitRandomNum(6);
		String  newfileName= newName + fileExtName;
		String relativeDirectory = "image";
		String relativePath = relativeDirectory+File.separator+newfileName;
		String relativeUrl = relativeDirectory+"/"+newfileName;
		
		String url = uploadUrlPath + relativeUrl;//获取文件对应的http地址
		String allPath = uploadFilePath + relativePath;//获取文件保存硬盘地址的绝对路径
		
		
		logger.info("allPath"+allPath);
		logger.info("url:"+url);
	
		File target = new File(allPath);
		if(!target.getParentFile().exists()){
			target.getParentFile().mkdirs();
		}
		file.transferTo(target);
		
		//如果是图片，则进行缩略图压缩
		if(checkImageType(file)){
			String iconFileName = newName+"_icon"+fileExtName;
			String relativePathIcon = relativeDirectory+File.separator+iconFileName;
			String allPathIcon = uploadFilePath+relativePathIcon;
			try {
				ImageCompressUtil.saveMinPhoto(allPath, allPathIcon, 600, 1.0d);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		
		FileInfo atFile  = new FileInfo();
		atFile.setOriginalName(oldFileName);
		atFile.setNewName(newfileName);
		atFile.setFileUrl(url);
		atFile.setBasePath(uploadFilePath);
		atFile.setMimeType(contentType);
		atFile.setRelativePath(relativePath);
		atFile.setRelativeUrl(relativeUrl);
		atFile.setCreateTime(new Date());
		atFile.setUpdateTime(new Date());
		
		return atFile;
	}

	/**
	 * 判断是否为图片文件
	 * @param myfile
	 * @return
	 */
	private boolean checkImageType(MultipartFile myfile){
		String fileType=myfile.getContentType();
		//boolean result= fileType.equals("image/gif")||fileType.equals("image/jpeg")||fileType.equals("image/png");
		if(fileType==null){
			return false;
		}
		
		boolean result = fileType.contains("image/");
		return result;
	}
	
	
	
	/*private boolean checkAchivesFileType(MultipartFile myfile){
		String oldName = myfile.getOriginalFilename();
		
		String extName =getFileExtName(oldName);
		if(".jpg".equalsIgnoreCase(extName) 
			|| ".gif".equalsIgnoreCase(extName) 
			|| ".bmp".equalsIgnoreCase(extName) 
			|| ".jpeg".equalsIgnoreCase(extName)
			|| ".png".equalsIgnoreCase(extName)
			|| ".xls".equalsIgnoreCase(extName)
			|| ".xlsx".equalsIgnoreCase(extName)
			|| ".xlsm".equalsIgnoreCase(extName)
			|| ".xlm".equalsIgnoreCase(extName)
			|| ".xlsx".equalsIgnoreCase(extName)
			|| ".xlsb".equalsIgnoreCase(extName)
			|| ".xlw".equalsIgnoreCase(extName)
			|| ".xlt".equalsIgnoreCase(extName)  
			|| ".pdf".equalsIgnoreCase(extName)
			|| ".docx".equalsIgnoreCase(extName)
			|| ".doc".equalsIgnoreCase(extName) ){
			return true;
		}
		
		return false;
	}
	
	
	private boolean checkVideoFileType(MultipartFile myfile){
		String oldName = myfile.getOriginalFilename();
		
		String extName =getFileExtName(oldName);
		if(".mp4".equalsIgnoreCase(extName) || ".mov".equalsIgnoreCase(extName)){
			return true;
		}
		
		return false;
	}
	
	private boolean checkApkFileType(MultipartFile myfile){
		String oldName = myfile.getOriginalFilename();
		
		String extName =getFileExtName(oldName);
		if(".apk".equalsIgnoreCase(extName) ){
			return true;
		}
		
		return false;
	}
	
	
	
	*/
	
	
	@RequestMapping(value="/uploadApk.do",method=RequestMethod.POST)  
	public @ResponseBody UploadFileResp uploadApk(@RequestParam MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(files==null || files.length<1){
			return new UploadFileResp(RespConstants.GLOBAL_OTHER, "文件未上传");
		}
		
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		
		for(MultipartFile fileItem:files ){
			
			FileInfo atFile = saveApk(fileItem);
			if(atFile==null){
				return new UploadFileResp(RespConstants.GLOBAL_OTHER);
			}
			fileList.add(atFile);
			
		}
		logger.info("fileList:"+fileList);
		fileService.insertBatch(fileList);
		
		String str[]=null;
		try {
			str = ApkUtils.parser(fileList.get(0).getBasePath()+fileList.get(0).getRelativePath());
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		
		//String imageUrl = "http://localhost:8080/hn3g3rd/"+uploadPath+"/"+newfileName;
//		resp.setVersion(str[0]);
//		resp.setBuildNumber(str[2]);
//		resp.setPackageName(str[1]);
		
		
//		String strResp =  JsonUtils.objectToJson(resp);
//		System.out.println("strResp:"+strResp);
		
		return new UploadFileResp(RespConstants.GLOBAL_SUCCESS,str[0],str[2],str[1],fileList);
	}
	
	
	
	
	
	public FileInfo saveApk(MultipartFile file) throws IllegalStateException, IOException{

		String oldFileName = file.getOriginalFilename();
		String fileExtName = FileUtils.getFileExtName(oldFileName);
		String contentType = file.getContentType();
		
		if (StringUtils.isEmpty(fileExtName)) {
			return null;
		}
		
		String newName= "" + System.currentTimeMillis()+ TextUtils.getNbitRandomNum(6);
		String  newfileName= newName + fileExtName;
		String relativeDirectory = "image";
		String relativePath = relativeDirectory+File.separator+newfileName;
		String relativeUrl = relativeDirectory+"/"+newfileName;
		
		String url = uploadUrlPath + relativeUrl;//获取文件对应的http地址
		String allPath = uploadFilePath + relativePath;//获取文件保存硬盘地址的绝对路径
		
		
		logger.info("allPath"+allPath);
		logger.info("url:"+url);
	
		File target = new File(allPath);
		if(!target.getParentFile().exists()){
			target.getParentFile().mkdirs();
		}
		file.transferTo(target);
		
		FileInfo atFile  = new FileInfo();
		atFile.setOriginalName(oldFileName);
		atFile.setNewName(newfileName);
		atFile.setFileUrl(url);
		atFile.setBasePath(uploadFilePath);
		atFile.setMimeType(contentType);
		atFile.setRelativePath(relativePath);
		atFile.setRelativeUrl(relativeUrl);
		atFile.setCreateTime(new Date());
		atFile.setUpdateTime(new Date());
		
		return atFile;
	}
	
	
}
