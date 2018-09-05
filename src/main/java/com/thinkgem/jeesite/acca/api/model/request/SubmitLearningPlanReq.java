package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 学习计划-提交学习规划请求参数
 * @author Young
 * @version 2016/8/11
 */
@ApiModel(value = "提交学习规划")
public class SubmitLearningPlanReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开始日期 yyyy-MM格式的字符串",required = true)
    private String startDate;
    @ApiModelProperty(value = "计划考试科目数/年.无概念为-1,其他对应相应的数字",required = true)
    private Integer planNum;		// 计划考试科目数/年
    @ApiModelProperty(value = "已考或面试科目的id列表")
    private List<Long> donotExamList;
    @ApiModelProperty(value = "所在学校类型,985为1;211为2;一本为3;二本为4;三本为5;大专为6;高中毕业为7")
    private Integer schoolType;
    @ApiModelProperty(value = "英语水平,值为高考90分以上1;英语3级为2;英语4级为3;英语6级为4;英语专8为5;雅思4.5-5.5为6;雅思6.0以上为7;托福70-90为8;托福90以上为9.")
    private Integer englishLevel;
    @ApiModelProperty(value = "当前身份,在校生为1;毕业生为2.")
    private Integer identity;

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }
        if (StringUtils.isEmpty(startDate) || planNum == null || donotExamList.size() < 2) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }
        //P4到P7的列表
        int flag = 0;
        for (int i = 0;i < donotExamList.size();i++){
            if (donotExamList.get(i) > 12) {
                flag++;
            }
        }
        if (flag < 2){
            return RespConstants.GLOBAL_PARAM_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    public List<Long> getDonotExamList() {
        return donotExamList;
    }

    public void setDonotExamList(List<Long> donotExamList) {
        this.donotExamList = donotExamList;
    }

    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    public Integer getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(Integer englishLevel) {
        this.englishLevel = englishLevel;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
