package com.thinkgem.jeesite.acca.constant;

import com.thinkgem.jeesite.freetek.api.constant.PublicConstants;

/**
 * web常量
 * @author Young
 * @version 2016/8/18
 */
public class WebConstant extends PublicConstants {

    /**
     * 文章分类类型
     * 类型：1html文章，2公开课
     */
    public static final int ARTICLECATEGORY_TYPE_HTML = 1;
    public static final int ARTICLECATEGORY_TYPE_OPEN = 2;

    /**
     * 文章类别：1有资有料html文章，2公开课，3学习达人分享html文章，4名师html文章
     */
    public static final int ARTICLE_TYPE_COMMON = 1;
    public static final int ARTICLE_TYPE_OPEN = 2;
    public static final int ARTICLE_TYPE_LEARNER = 3;
    public static final int ARTICLE_TYPE_TEACHER = 4;
    public static final int ARTICLE_TYPE_H5 = 5;

    /**
     * 文章分类是否为系统数据
     * 是否系统数据：1不是，2是，系统数据不允许删除
     */
    public static final int ARTICLECATEGORY_NOT_SYSDATA = 1;
    public static final int ARTICLECATEGORY_IS_SYSDATA = 2;
    
    public static final String START_REV = "开始审核";
    public static final String FIND = "查看";

    /**
     * 消息推送,推送对象
     * 0所有用户;1待考试用户
     */
    public static final int MSG_PUSH_PEOPLE_ALL = 0;
    public static final int MSG_PUSH_PEOPLE_EXAM = 1;

    /**
     * 消息已读状态。0未读，1已读
     */
    public static final int MSG_PUSH_READ_NO = 0;
    public static final int MSG_PUSH_READ_YES = 1;

    /**
     * 代注册的身份证件类型
     */
    public static final int CARD_TYPE_IDCARD = 1;
    public static final int CARD_TYPE_PASSPORT = 2;
}