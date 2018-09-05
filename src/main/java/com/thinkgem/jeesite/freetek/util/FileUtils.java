package com.thinkgem.jeesite.freetek.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.thinkgem.jeesite.common.config.Global;


/**
 * 文件操作相关
 * 包括：文件保存，文件zip压缩，文件拷贝复制等。
 * @author ivan
 *
 */
public class FileUtils {
	
	private static Logger logger = Logger.getLogger(FileUtils.class);
	
	
	
	/**
	 * 获取文件后缀名，包括“.”,返回值如“.jpg”
	 * @param fileName
	 * @return
	 */
	public static String getFileExtName (String fileName) {
		
		if (fileName == null || fileName.length() < 1) {
			return null;
		}
		int lastIndex = fileName.lastIndexOf(".");

		return fileName.substring(lastIndex, fileName.length());
	}
	
	
	
	
	/**
	 * 使用MultipartFile的transferTo方法保存文件到指定目录和文件名
	 * @param MultipartFile file 文件信息
	 * @param filePath 保存后的完整文件（包括路径和文件名）
	 * @return
	 */
	public static boolean saveFile(MultipartFile file,String filePath){
		if(!file.isEmpty()){
			try {
				file.transferTo(new File(filePath));
				return true;
			}catch (Exception e) {
				logger.error(e.getMessage(),e);
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 使用FileOutputStream方法保存文件到指定目录和文件名
	 * @param String filePath 保存后的文件夹目录
	 * @param String fileName 保存后的文件名
	 * @param MultipartFile file 文件信息
	 * @return
	 */
	public static boolean writeFile(String filePath,String fileName,MultipartFile file){
		File fileDir =new File(filePath);     
		if(!fileDir.exists()  && !fileDir.isDirectory()){
			boolean f = fileDir.mkdirs();  
		    if(!f){
		    	return false;
		    }
		}
		FileOutputStream output=null;
		try {
			byte[] bytes = file.getBytes();
			output = new FileOutputStream(new File(filePath+fileName));
			output.write(bytes);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}finally{
			if(output!=null){
				try {
					output.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return true;
	}
	
	
	
	
	
	////////////////以下为图片压缩相关/////////////////////////////
	/**
	 * 文件压缩
	 * @param inputFileName  输入文件
	 * @param outputFileName 压缩后输出文件
	 * @throws Exception
	 */
	public static void zip(String inputFileName,String outputFileName) throws Exception {
        System.out.println(outputFileName);
        zip(outputFileName, new File(inputFileName));
    }

    private static void zip(String outputFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFileName));
        zip(out, inputFile, "");
        System.out.println("zip done");
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
           File[] fl = f.listFiles();
           out.putNextEntry(new ZipEntry(base + "/"));
           base = base.length() == 0 ? "" : base + "/";
           for (int i = 0; i < fl.length; i++) {
           zip(out, fl[i], base + fl[i].getName());
         }
        }else {
           out.putNextEntry(new ZipEntry(base));
           FileInputStream in = new FileInputStream(f);
           int b;
           System.out.println(base);
           while ( (b = in.read()) != -1) {
            out.write(b);
         }
         in.close();
       }
    }
    
    ///////////////////////////////压缩end/////////////////////////////
    
    
    
    /**
     * 用通道进行文件拷贝
     * @param files  源文件
     * @param outFile  目的文件（即拷贝后的文件）
     */
    public static boolean fileChannelCopy(List<File> files, File outFile) {
        FileOutputStream fo = null;
        FileChannel outChannel = null;
        FileChannel inChannel=null;
        try {
            fo = new FileOutputStream(outFile);
            outChannel = fo.getChannel();//得到对应的文件通道
            for(File file : files){
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0, inChannel.size(), outChannel);
                inChannel.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
            	inChannel.close();
                fo.close();
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * 拷贝单个文件并重命名
     * @param String sourceFile 源文件 完整路径
     * @param String destFile  拷贝后重命名文件 完整路
     * @return 是否成功
     */
    public static boolean copyAndRenameFile(String sourceFile,String destFile){
    	
    	return copyAndRenameFile(sourceFile, destFile,null);
    }
    
    
    /**
     * 拷贝单个文件，并和另外一个文件合并并重命名
     * @param String sourceFile 源文件 完整路径
     * @param String destFile  拷贝后重命名文件 完整路
     * @param String anotherFile  另外一个文件，将和sourceFile文件一块合并
     * @return 是否成功
     */
    public static boolean copyAndRenameFile(String sourceFile,String destFile,String anotherFile){
    	File inFile = new File(sourceFile);
    	
    	File outFile = new File(destFile);
    	
    	List<File> inList = new ArrayList<File>();
    	inList.add(inFile);
    	if(!StringUtils.isEmpty(anotherFile)){
    		File inOtherFile = new File(anotherFile);
    		inList.add(inOtherFile);
    	}
    	
    	return fileChannelCopy(inList, outFile);
    }
    
    /**
     * 拷贝真个文件夹，将源文件夹下所有文件拷贝到目标文件见下
     * @param String sourceForder 原文件夹目录
     * @param String destForder   目标文件夹目录
     */
    public static void copyForder(String sourceForder,String destForder){
    	File sourForderFile = new File(sourceForder);
    	File destForderFile = new File(destForder);
    	
    	if(!sourForderFile.isDirectory()){
    		return;
    	}
    	
    	if(!destForderFile.exists()){
    		destForderFile.mkdirs();
    	}
    	
    	File[] fileList = sourForderFile.listFiles();
    	
    	if(fileList==null || fileList.length==0){
    		return;
    	}
    	
    	for(File index:fileList){
    		if(index.isFile()){
    			String name = index.getName();
    			copyAndRenameFile(sourceForder+File.separator+name, destForder+File.separator+name);
    			
    		}else{
    			String newSourceForder = index.getPath();
    			String newDestForder = newSourceForder.replace(sourceForder, destForder);
    			copyForder(newSourceForder,newDestForder);
    		}
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    //////////////////////////////////////////////////////以下函数自用，不需要看///////////////////////////////////////////////////////
    
    /**
     * 对文件夹内的所有文件合并并且重命名
     * @param forderPath 文件夹目录
     */
    public static void copyForderAllFile(String forderPath){
    	
    	File file = new File(forderPath);
    	copyAndRenameForderFile(file,"F:\\movies\\1.txt");
    	
    }
    
    
    
    /**
     * 对文件夹内的所有文件合并并且重命名
     * @param forderPath 文件夹目录
     * @param  String anotherFilePath 拷贝的文件是否需要和另外一个文件进行合并。如果该字段为null则表示只是将该文件夹下所有文件进行了拷贝并重命名
     */
    public static void copyAndRenameForderFile(String forderPath,String anotherFilePath){
    	
    	File file = new File(forderPath);
    	copyAndRenameForderFile(file,anotherFilePath);
    	
    }
    
    
    /**
     * 对文件夹内的所有文件合并并且重命名
     * @param  File file 文件夹对应的File
     * @param  String anotherFilePath 拷贝的文件是否需要和另外一个文件进行合并。
     */
    public static void copyAndRenameForderFile(File file,String anotherFilePath){
    	if(!file.isDirectory()){
    		return;
    	}
    	
    	File[] fileList = file.listFiles();
    	
    	if(fileList==null || fileList.length==0){
    		//System.out.println("文件夹："+file.getPath()+"内没有文件");
    		return;
    	}
    	
    	
    	for(File index:fileList){
    		if(index.isFile()){
    			String oldName = index.getPath();
    			int i = oldName.lastIndexOf(".");
    			String ext = oldName.substring(i);
    			String newName = oldName.substring(0,i)+"----"+ext;
    			
    			System.out.println(newName);
    			
    			File newFile = new File(newName);
    			
    			
    			List<File> list = new ArrayList<File>();
    			list.add(index);
    			if(!StringUtils.isEmpty(anotherFilePath)){
    				list.add(new File(anotherFilePath));
    			}
    			
    			fileChannelCopy(list, newFile);
    			
    		}else{
    			
    			
    			copyAndRenameForderFile(index,anotherFilePath);
    		}
    	}
    }
    
    
    /**
     * 使用富文本生成html静态页面，返回静态页面的绝对url
     * @param content
     * @return
     */
    public static String generateHtml(String content){
    	
    	String htmlSrc = getHtmlSrc(content);
    	logger.info("htmlSrc:"+htmlSrc);
    	String baseDir = Global.getProjectBasDir();
    	String baseUrl = Global.getProjectBaseUrl();
    	
    	String fileName = System.currentTimeMillis()+ TextUtils.getNbitRandomNum(6) +".html";
    	
    	String fileDir = baseDir+"html5"+File.separator+fileName;
    	String url = baseUrl+"html5/"+fileName;
    	
    	writeFile(htmlSrc, fileDir);
    	
    	return url;
    }
    
    /**
     * 生成静态文件源码
     * @param content
     * @return
     */
    public static String getHtmlSrc(String content){
    	String contentUn = HtmlUtils.htmlUnescape(content);
    	StringBuffer sbuf = new StringBuffer();
    	sbuf.append("<html>")
    			.append("<head>")
    				.append("<title>成长记忆</title>")
    				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />")
    				.append("<meta name=\"robots\" content=\"noindex, nofollow\" />")
    			.append("</head>")
    			.append("<body style=\"font-size:62.5%;\">")
    				.append(contentUn)
    			.append("</body>")
    			.append("</html>");
    	
    	
    	return sbuf.toString();
    }
    
    
    public static void writeFile(String content,String fileName) {  
        try {  
        	File file = new File(fileName);
        	if(!file.exists()){
        		file.createNewFile();
        	}
            FileOutputStream out = new FileOutputStream(file); // 输出文件路径 
            OutputStreamWriter write = new OutputStreamWriter(out,"UTF-8");
            write.write(content);  
            write.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
   
}
