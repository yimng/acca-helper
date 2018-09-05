package com.thinkgem.jeesite.acca.constant;

import com.thinkgem.jeesite.freetek.api.constant.PublicConstants;

/**
 * api常量
 * @author Young
 * @version 2016/8/9
 */
public class ApiConstant extends PublicConstants {

    /**
     * 代注册状态
     * 1待付款，2待审核，3审核通过，4审核不通过，5注册成功
     */
    public static final int REGISTER_OBLIGATION = 1;//待付款
    public static final int REGISTER_AUDIT = 2;//待审核
    public static final int REGISTER_EGIS = 3;//审核通过
    public static final int REGISTER_NOT_PASS = 4;//审核未通过
    public static final int REGISTER_SUCCESS = 5;//注册成功

    /**
     * 代注册类型
     * 1.acca注册，2.fia注册
     */
    public static final int REGISTER_TYPE_ACCA = 1;
    public static final int REGISTER_TYPE_FIA = 2;

    /**
     * 代注册身份类型
     * 1.在校生，2.毕业生，3.国外院校毕业生
     */
    public static final int IDENTITY_TYPE_UNDERGRADUATE = 1;
    public static final int IDENTITY_TYPE_GRADUATE = 2;
    public static final int IDENTITY_TYPE_FOREIGNGRADUATE = 3;

    /**
     * 考试类型
     * 1自有考试，2官方机考，4官方笔考,8中博诚通分部
     */
    public static final int EXAM_TYPE_SELF = 1;
    public static final int EXAM_TYPE_OFFICIAL_TEST = 2;
    public static final int EXAM_TYPE_OFFICIAL_PENTEST = 4;
    public static final int EXAM_TYPE_ZHONGCHENG = 8;

    /**
     * 提醒类型
     * 1.考试提醒;2.规划提醒;3.个人提醒
     */
    public static final int TIP_TYPE_EXAM = 1;
    public static final int TIP_TYPE_PLAN = 2;
    public static final int TIP_TYPE_PERSON = 3;

    /**
     * 提醒类型
     * 0.系统提醒;1.个人提醒
     */
    public static final int TIP_NOT_SYS = 0;
    public static final int TIP_IS_SYS = 1;

    /**
     * 提醒时间
     * 1.提前一天;2.提前一周
     */
    public static final int TIP_BEFORE_DAY = 1;
    public static final int TIP_BEFORE_WEEK = 2;

}