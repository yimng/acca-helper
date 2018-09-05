package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代注册参数
 * @author Young
 * @version 2016/8/9
 */
@ApiModel(value = "代注册保存/修改请求参数")
public class SaveAccaRegisterReq extends BaseRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "代注册id")
    private Long accaRegisterId;
    @ApiModelProperty(value = "注册类型：1acca注册，2fia注册",required = true)
    private Integer registerType;		// 注册类型：1acca注册，2fia注册
    @ApiModelProperty(value = "姓名",required = true)
    private String name;		// 姓名
    @ApiModelProperty(value = "性别：1男，2女",required = true)
    private Integer sex;		// 性别：1男，2女
    @ApiModelProperty(value = "身份证明类型:1.身份证;2.护照",required = true)
    private Integer cardType;
    @ApiModelProperty(value = "身份证号码",required = true)
    private String cardNumber;		// 身份证号码
    @ApiModelProperty(value = "手机号",required = true)
    private String phone;		// 手机号
    @ApiModelProperty(value = "邮箱",required = true)
    private String email;		// 邮箱
    @ApiModelProperty(value = "距离最近城市id",required = true)
    private String cityId;		// 距离最近城市id
    @ApiModelProperty(value = "身份类型：1在校生，2毕业生，3国外院校毕业生")
    private Integer identityType;		// 身份类型：1在校生，2毕业生，3国外院校毕业生
    @ApiModelProperty(value = "2寸白底彩色照片",required = true)
    private Long whiteColorImgId;		// 2寸白底彩色照片
    @ApiModelProperty(value = "身份证明原件id",required = true)
    private Long identityCardOgImgId;		// 身份证明原件id
    @ApiModelProperty(value = "身份证明翻译件",required = true)
    private Long identityCardTrImgId;		// 身份证明翻译件
    @ApiModelProperty(value = "在读证明图片id")
    private Long readingCertificateImgId;		// 在读证明图片id
    @ApiModelProperty(value = "在读证明翻译件图片id")
    private Long readingCertificateTrImgId;		// 在读证明翻译件图片id
    @ApiModelProperty(value = "成绩单原件图片id")
    private Long examsResultOgImgId;		// 成绩单原件图片id
    @ApiModelProperty(value = "成绩单翻译件图片id")
    private Long examsResultTrImgId;		// 成绩单翻译件图片id
    @ApiModelProperty(value = "毕业证书或学位证书原件图片id")
    private Long graduateCertifcateOgImgId;		// 毕业证书或学位证书原件图片id
    @ApiModelProperty(value = "毕业证书或学位证书翻译件图片id")
    private Long graduateCertifcateTrImgId;		// 毕业证书或学位证书翻译件图片id
    @ApiModelProperty(value = "在校生学校")
    private String readingSchool;			//城市名称
    @ApiModelProperty(value = "在校生专业")
    private String readingMajor;			//城市名称
    @ApiModelProperty(value = "在校生年级")
    private String readingGrade;			//城市名称

    @Override
    public int isCorrectParams() {
        if(super.isCorrectParams()!= RespConstants.GLOBAL_SUCCESS){
            return super.isCorrectParams();
        }

        //判断代注册信息的参数
        if (StringUtils.isEmpty(name)
                || sex == null
                || cardType == null
                || StringUtils.isEmpty(cardNumber)
                || StringUtils.isEmpty(phone)
                || StringUtils.isEmpty(email)
                || cityId == null){
            return RespConstants.GLOBAL_PARAM_ERROR;
        }
        //图片校验
        if (accaRegisterId == null || accaRegisterId == 0){
            if (whiteColorImgId == null
                    || identityCardOgImgId == null
                    || identityCardTrImgId == null){
                return RespConstants.ACCA_REGISTER_IMG;
            }
            //还需要根据代注册的类型判断所填的参数是否正确
            if (registerType == ApiConstant.REGISTER_TYPE_ACCA){
                //如果是ACCA注册,还需要根据注册人的身份进行参数校验
                if (identityType == null ||
                        //如果为在读大学生,则在读证明和成绩单相关不能为空
                        (identityType == ApiConstant.IDENTITY_TYPE_UNDERGRADUATE &&
                                (readingCertificateImgId == null ||
                                        examsResultOgImgId == null ||
                                        examsResultTrImgId == null ))
                        ||	//如果为毕业生,则学位证书相关不能为空
                        (identityType == ApiConstant.IDENTITY_TYPE_GRADUATE &&
                                (graduateCertifcateOgImgId == null ||
                                        graduateCertifcateTrImgId == null))
                        ||	//如果为国外院校毕业生,则学历证书相关和成绩单相关不能为空
                        (identityType == ApiConstant.IDENTITY_TYPE_FOREIGNGRADUATE &&
                                (graduateCertifcateOgImgId == null ||
                                        graduateCertifcateTrImgId == null ||
                                        examsResultOgImgId == null ||
                                        examsResultTrImgId == null))
                        ){
                    return RespConstants.ACCA_REGISTER_IMG;
                }
            }
        }

        if(this.cardType == 1){
	        if(StringUtils.isEmpty(this.cardNumber) || !IdcardUtils.validateCard(this.cardNumber)){
				return RespConstants.EXAM_IDCARD_ERROR;
			}
        }
        
        //手机号校验
        Pattern phonePatten = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$");
        Matcher phoneMatcher = phonePatten.matcher(phone);
        boolean matches = phoneMatcher.matches();
        if (!matches) {
            return RespConstants.EXAM_MOBILE_ERROR;
        }
        //邮箱校验
        Pattern emailPatten = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher emailMatcher = emailPatten.matcher(email);
        boolean emailFlag = emailMatcher.matches();
        if (!emailFlag) {
            return RespConstants.EXAM_EMAIL_ERROR;
        }

        return RespConstants.GLOBAL_SUCCESS;
    }

    public Long getAccaRegisterId() {
        return accaRegisterId;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public Long getWhiteColorImgId() {
        return whiteColorImgId;
    }

    public void setWhiteColorImgId(Long whiteColorImgId) {
        this.whiteColorImgId = whiteColorImgId;
    }

    public Long getIdentityCardOgImgId() {
        return identityCardOgImgId;
    }

    public void setIdentityCardOgImgId(Long identityCardOgImgId) {
        this.identityCardOgImgId = identityCardOgImgId;
    }

    public Long getIdentityCardTrImgId() {
        return identityCardTrImgId;
    }

    public void setIdentityCardTrImgId(Long identityCardTrImgId) {
        this.identityCardTrImgId = identityCardTrImgId;
    }

    public Long getReadingCertificateImgId() {
        return readingCertificateImgId;
    }

    public void setReadingCertificateImgId(Long readingCertificateImgId) {
        this.readingCertificateImgId = readingCertificateImgId;
    }

    public Long getExamsResultOgImgId() {
        return examsResultOgImgId;
    }

    public void setExamsResultOgImgId(Long examsResultOgImgId) {
        this.examsResultOgImgId = examsResultOgImgId;
    }

    public Long getExamsResultTrImgId() {
        return examsResultTrImgId;
    }

    public void setExamsResultTrImgId(Long examsResultTrImgId) {
        this.examsResultTrImgId = examsResultTrImgId;
    }

    public Long getGraduateCertifcateOgImgId() {
        return graduateCertifcateOgImgId;
    }

    public void setGraduateCertifcateOgImgId(Long graduateCertifcateOgImgId) {
        this.graduateCertifcateOgImgId = graduateCertifcateOgImgId;
    }

    public Long getGraduateCertifcateTrImgId() {
        return graduateCertifcateTrImgId;
    }

    public void setGraduateCertifcateTrImgId(Long graduateCertifcateTrImgId) {
        this.graduateCertifcateTrImgId = graduateCertifcateTrImgId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public void setAccaRegisterId(Long accaRegisterId) {
        this.accaRegisterId = accaRegisterId;
    }

    public Long getReadingCertificateTrImgId() {
        return readingCertificateTrImgId;
    }

    public void setReadingCertificateTrImgId(Long readingCertificateTrImgId) {
        this.readingCertificateTrImgId = readingCertificateTrImgId;
    }

    public String getReadingSchool() {
        return readingSchool;
    }

    public void setReadingSchool(String readingSchool) {
        this.readingSchool = readingSchool;
    }

    public String getReadingMajor() {
        return readingMajor;
    }

    public void setReadingMajor(String readingMajor) {
        this.readingMajor = readingMajor;
    }

    public String getReadingGrade() {
        return readingGrade;
    }

    public void setReadingGrade(String readingGrade) {
        this.readingGrade = readingGrade;
    }
}
