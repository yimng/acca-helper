package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 删除个人日历提醒参数
 * @author Young
 * @version 2016/9/7
 */
@ApiModel(value = "删除个人日历提醒参数")
public class DelCalendarTipsReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提醒id",required = true)
    private Long id;		// 提醒id
    @ApiModelProperty(value = "是否为系统消息,0为不是;1为是",required = true)
    private Integer isSys;

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }
        if (id == null || isSys == ApiConstant.TIP_IS_SYS) {
            return RespConstants.GLOBAL_PARAM_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }
}
