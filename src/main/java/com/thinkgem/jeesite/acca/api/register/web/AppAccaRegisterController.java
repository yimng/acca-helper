package com.thinkgem.jeesite.acca.api.register.web;

import com.thinkgem.jeesite.acca.api.model.request.SaveAccaRegisterPayReq;
import com.thinkgem.jeesite.acca.api.model.request.SaveAccaRegisterReq;
import com.thinkgem.jeesite.acca.api.model.response.SaveAccaRegisterResp;
import com.thinkgem.jeesite.acca.api.register.entity.AppAccaRegister;
import com.thinkgem.jeesite.acca.api.register.entity.AppExamOpenCity;
import com.thinkgem.jeesite.acca.api.register.service.AppAccaRegisterService;
import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代注册模块
 * @author Young
 * @version 2016/8/9
 */
@Api(value = "${apiPath}/register", description = "代注册模块")
@Controller
@RequestMapping(value = "${apiPath}/register")
public class AppAccaRegisterController extends BaseController {

    @Autowired
    private AppAccaRegisterService appAccaRegisterService;

    @ApiOperation(value = "代注册－获取信息", httpMethod = "POST", notes = "获取代注册信息", position=1)
    @RequestMapping(value = "/getAccaRegisterInfo.do")
    public @ResponseBody
    BaseObjResponse<AppAccaRegister> getAccaRegisterInfo(@RequestBody BaseRequest req){
        int correctParams = req.isCorrectParams();
        if (correctParams != RespConstants.GLOBAL_SUCCESS) {
            return new BaseObjResponse<AppAccaRegister>(correctParams);
        }
        BaseObjResponse<AppAccaRegister> resp = appAccaRegisterService.getRegisterInfo(req);
        return resp;
    }

    @ApiOperation(value = "代注册－获取距离最近城市", httpMethod = "POST", notes = "获取距离最近城市", position=2)
    @RequestMapping(value = "/getRegisterCityList.do")
    public @ResponseBody
    BasePageResponse<AppExamOpenCity> getRegisterCity(@RequestBody BaseRequest req){
        BasePageResponse<AppExamOpenCity> resp = appAccaRegisterService.getRegisterCity(req);
        return resp;
    }

    @ApiOperation(value = "代注册－提交/修改", httpMethod = "POST", notes = "代注册－提交/修改", position=3)
    @RequestMapping(value = "/saveAccaRegister.do")
    public @ResponseBody SaveAccaRegisterResp saveAccaRegister(@RequestBody SaveAccaRegisterReq req) {
    	SaveAccaRegisterResp resp = appAccaRegisterService.saveAccaRegister(req);
        return resp;
    }

    @ApiOperation(value = "代注册－提交/修改V1.7.0", httpMethod = "POST", notes = "代注册－提交/修改V1.7.0", position=3)
    @RequestMapping(value = "/saveAccaRegister_V170.do")
    public @ResponseBody SaveAccaRegisterResp saveAccaRegister_V170(@RequestBody SaveAccaRegisterReq req) {
        //基础参数校验
        int correctParams = req.isCorrectParams();
        if (correctParams != RespConstants.GLOBAL_SUCCESS) {
            return new SaveAccaRegisterResp(correctParams);
        }
        if (req.getAccaRegisterId() == null || req.getAccaRegisterId() == 0) {
            //添加对新版本新增字段的数据校验
            if (req.getRegisterType() == ApiConstant.REGISTER_TYPE_ACCA && req.getIdentityType() == ApiConstant.IDENTITY_TYPE_UNDERGRADUATE) {
                //在读证明翻译件不能为空
                if (req.getReadingCertificateTrImgId() == null) {
                    return new SaveAccaRegisterResp(RespConstants.ACCA_REGISTER_IMG);
                }
                //在校生学校,专业,年级不能为空
                if (StringUtils.isEmpty(req.getReadingSchool()) ||
                        StringUtils.isEmpty(req.getReadingMajor()) ||
                        StringUtils.isEmpty(req.getReadingGrade())) {
                    return new SaveAccaRegisterResp(RespConstants.GLOBAL_PARAM_ERROR);
                }
            }
        }
        //提交代注册信息
        SaveAccaRegisterResp resp = appAccaRegisterService.saveAccaRegister(req);
        return resp;
    }

    @ApiOperation(value = "代注册－上传支付凭证", httpMethod = "POST", notes = "代注册－上传支付凭证", position=4)
    @RequestMapping(value = "/saveAccaRegisterPay.do")
    public @ResponseBody
    BaseResponse saveAccaRegisterPay(@RequestBody SaveAccaRegisterPayReq req) {
        int correctParams = req.isCorrectParams();
        if (correctParams != RespConstants.GLOBAL_SUCCESS) {
            return new BaseResponse(correctParams);
        }
        BaseResponse resp = appAccaRegisterService.saveAccaRegisterPay(req);
        return resp;
    }

    @ApiOperation(value = "代注册－删除注册信息", httpMethod = "POST", notes = "代注册－删除注册信息", position=5)
    @RequestMapping(value = "/delRegisterInfo.do")
    public @ResponseBody
    BaseResponse delRegisterInfo(@RequestBody BaseRequest req) {
        int correctParams = req.isCorrectParams();
        if (correctParams != RespConstants.GLOBAL_SUCCESS) {
            return new BaseResponse(correctParams);
        }
        BaseResponse resp = appAccaRegisterService.delRegisterInfo(req);
        return resp;
    }
}
