package com.thinkgem.jeesite.modules.sys.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.thinkgem.jeesite.modules.sys.service.SystemService;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
//		System.out.println("initWebApplicationContext："+servletContext.getServletNames().toString());
//		System.out.println("request用户名："+servletContext.getAttribute("username"));
//		//System.out.println("用户名/密码：" + username + "/" + password);
//		Map map = new HashMap();  
//	    Enumeration paramNames = servletContext.getAttributeNames();  
//	    while (paramNames.hasMoreElements()) {  
//	      String paramName = (String) paramNames.nextElement();  	  
//	      String paramValue = servletContext.getInitParameter(paramName);  
//	      if (paramValue!= null) {  	       
//	          System.out.println("参数：" + paramName + "=" + paramValue);    
//	      } 
//	    }  
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
