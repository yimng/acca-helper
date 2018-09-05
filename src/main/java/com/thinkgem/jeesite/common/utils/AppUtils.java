/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils;

/**
 * 手机app API常用工具类
 * @author lookjun
 * @version 2016-07-25
 */
public class AppUtils extends org.apache.commons.lang3.StringUtils {
    
    /**
     * 验证是否是正确的手机号格式
     * @param mobile
     * @return
     */
    public static boolean isMobileNum(String mobile) {
    	return mobile.matches("^(1)\\d{10}$");
    }
    
    
}
