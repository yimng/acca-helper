/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 代注册Entity
 * @author Young
 * @version 2016-08-09
 */
@ApiModel("代注册信息")
public class AppAccaRegister extends DataEntity<AppAccaRegister> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "代注册id")
	private Long accaRegisterId;		// acca_register_id
	@ApiModelProperty(value = "用户id")
	private Long accaUserId;		// acca注册用户id
	@ApiModelProperty(value = "注册类型：1acca注册，2fia注册")
	private Integer registerType;		// 注册类型：1acca注册，2fia注册
	@ApiModelProperty(value = "姓名",required = true)
	private String name;		// 姓名
	@ApiModelProperty(value = "性别：1男，2女",required = true)
	private Integer sex;		// 性别：1男，2女
	@ApiModelProperty(value = "身份证明类型:1.身份证;2.护照",required = true)
	private Integer cardType;
	@ApiModelProperty(value = "身份证明号码",required = true)
	private String cardNumber;		// 身份证号码
	@ApiModelProperty(value = "手机号",required = true)
	private String phone;		// 手机号
	@ApiModelProperty(value = "邮箱",required = true)
	private String email;		// 邮箱
	@ApiModelProperty(value = "城市id",required = true)
	private Integer cityId;		// 距离最近城市id
	@ApiModelProperty(value = "身份类型：1在校生，2毕业生，3国外院校毕业生")
	private Integer identityType;		// 身份类型：1在校生，2毕业生，3国外院校毕业生
	@ApiModelProperty(value = "2寸白底彩色照片")
	private Long whiteColorImgId;		// 2寸白底彩色照片
	@ApiModelProperty(value = "身份证明原件id")
	private Long identityCardOgImgId;		// 身份证明原件id
	@ApiModelProperty(value = "身份证明翻译件")
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
	@JsonIgnore
	private Integer status;		// 状态：1待付款，2待审核，3审核通过，4审核不通过，5注册成功
	@ApiModelProperty(value = "审核状态:1待付款，2待审核，4审核不通过，5注册成功")
	private Integer clientStatus;
	@ApiModelProperty(value = "注册缴纳费用")
	private BigDecimal payAmout;		// 注册缴纳费用，如900.00元
	@ApiModelProperty(value = "支付凭证截图id")
	private Long payImgId;		// 支付凭证截图id
	@ApiModelProperty(value = "ACCA学员账号")
	private String accaRegisterName;		// acca学员账号
	@ApiModelProperty(value = "ACCA密码")
	private String accaRegisterPassword;		// acca密码
	@JsonIgnore
	private String checkPersonId;       //审核人id
	@ApiModelProperty(value = "审核人姓名")
	private String checkPersonName;			//审核人姓名
	@JsonIgnore
	private Date checkTime;			//审核时间
	@ApiModelProperty(value = "审核不通过原因")
	private String checkReason;			//审核不通过原因
	@ApiModelProperty(value = "城市名称")
	private String cityName;			//城市名称
	@ApiModelProperty(value = "在校生学校")
	private String readingSchool;			//城市名称
	@ApiModelProperty(value = "在校生专业")
	private String readingMajor;			//城市名称
	@ApiModelProperty(value = "在校生年级")
	private String readingGrade;			//城市名称

	@ApiModelProperty(value = "acca注册用2寸白底彩色照片")
	private FileInfo whiteColorImg;
	@ApiModelProperty(value = "身份证明原件")
	private FileInfo identityCardOgImg;
	@ApiModelProperty(value = "身份证明翻译件")
	private FileInfo identityCardTrImg;		// 身份证明翻译件
	@ApiModelProperty(value = "在读证明图片")
	private FileInfo readingCertificateImg;		// 在读证明图片id
	@ApiModelProperty(value = "在读证明翻译件图片")
	private FileInfo readingCertificateTrImg;		// 在读证明图片id
	@ApiModelProperty(value = "成绩单原件图片")
	private FileInfo examsResultOgImg;		// 成绩单原件图片id
	@ApiModelProperty(value = "成绩单翻译件图片")
	private FileInfo examsResultTrImg;		// 成绩单翻译件图片id
	@ApiModelProperty(value = "毕业证书或学位证书原件图片")
	private FileInfo graduateCertifcateOgImg;		// 毕业证书或学位证书原件图片id
	@ApiModelProperty(value = "毕业证书或学位证书翻译件图片")
	private FileInfo graduateCertifcateTrImg;		// 毕业证书或学位证书翻译件图片id
	@ApiModelProperty(value = "支付凭证截图")
	private FileInfo payImg;		// 支付凭证截图id


	public AppAccaRegister() {
		super();
	}

	public AppAccaRegister(String id){
		super(id);
	}

	@NotNull(message="acca_register_id不能为空")
	public Long getAccaRegisterId() {
		return accaRegisterId;
	}

	public void setAccaRegisterId(Long accaRegisterId) {
		this.accaRegisterId = accaRegisterId;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	@Length(min=0, max=4, message="注册类型：1acca注册，2fia注册长度必须介于 0 和 4 之间")
	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=4, message="性别：1男，2女长度必须介于 0 和 4 之间")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="身份证号码长度必须介于 0 和 64 之间")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Length(min=0, max=32, message="手机号长度必须介于 0 和 32 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="邮箱长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="距离最近城市id长度必须介于 0 和 64 之间")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=4, message="身份类型：1在校生，2毕业生，3国外院校毕业生长度必须介于 0 和 4 之间")
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
	
	@Length(min=1, max=4, message="状态：1待付款，2待审核，3审核通过，4审核不通过，5注册成功长度必须介于 1 和 4 之间")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public BigDecimal getPayAmout() {
		return payAmout;
	}

	public void setPayAmout(BigDecimal payAmout) {
		this.payAmout = payAmout;
	}
	
	public Long getPayImgId() {
		return payImgId;
	}

	public void setPayImgId(Long payImgId) {
		this.payImgId = payImgId;
	}
	
	@Length(min=0, max=64, message="acca学院号长度必须介于 0 和 64 之间")
	public String getAccaRegisterName() {
		return accaRegisterName;
	}

	public void setAccaRegisterName(String accaRegisterName) {
		this.accaRegisterName = accaRegisterName;
	}
	
	@Length(min=0, max=64, message="acca密码长度必须介于 0 和 64 之间")
	public String getAccaRegisterPassword() {
		return accaRegisterPassword;
	}

	public void setAccaRegisterPassword(String accaRegisterPassword) {
		this.accaRegisterPassword = accaRegisterPassword;
	}

	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckPersonName() {
		return checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public FileInfo getPayImg() {
		return payImg;
	}

	public void setPayImg(FileInfo payImg) {
		this.payImg = payImg;
	}

	public FileInfo getWhiteColorImg() {
		return whiteColorImg;
	}

	public void setWhiteColorImg(FileInfo whiteColorImg) {
		this.whiteColorImg = whiteColorImg;
	}

	public FileInfo getIdentityCardOgImg() {
		return identityCardOgImg;
	}

	public void setIdentityCardOgImg(FileInfo identityCardOgImg) {
		this.identityCardOgImg = identityCardOgImg;
	}

	public FileInfo getIdentityCardTrImg() {
		return identityCardTrImg;
	}

	public void setIdentityCardTrImg(FileInfo identityCardTrImg) {
		this.identityCardTrImg = identityCardTrImg;
	}

	public FileInfo getReadingCertificateImg() {
		return readingCertificateImg;
	}

	public void setReadingCertificateImg(FileInfo readingCertificateImg) {
		this.readingCertificateImg = readingCertificateImg;
	}

	public FileInfo getExamsResultOgImg() {
		return examsResultOgImg;
	}

	public void setExamsResultOgImg(FileInfo examsResultOgImg) {
		this.examsResultOgImg = examsResultOgImg;
	}

	public FileInfo getExamsResultTrImg() {
		return examsResultTrImg;
	}

	public void setExamsResultTrImg(FileInfo examsResultTrImg) {
		this.examsResultTrImg = examsResultTrImg;
	}

	public FileInfo getGraduateCertifcateOgImg() {
		return graduateCertifcateOgImg;
	}

	public void setGraduateCertifcateOgImg(FileInfo graduateCertifcateOgImg) {
		this.graduateCertifcateOgImg = graduateCertifcateOgImg;
	}

	public FileInfo getGraduateCertifcateTrImg() {
		return graduateCertifcateTrImg;
	}

	public void setGraduateCertifcateTrImg(FileInfo graduateCertifcateTrImg) {
		this.graduateCertifcateTrImg = graduateCertifcateTrImg;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getClientStatus() {
		if (status == 3){
			this.clientStatus = 2;
		} else {
			this.clientStatus = status;
		}
		return clientStatus;
	}

	public void setClientStatus(Integer status) {
		if (status == 3){
			this.clientStatus = 2;
		} else {
			this.clientStatus = status;
		}
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getReadingCertificateTrImgId() {
		return readingCertificateTrImgId;
	}

	public void setReadingCertificateTrImgId(Long readingCertificateTrImgId) {
		this.readingCertificateTrImgId = readingCertificateTrImgId;
	}

	public FileInfo getReadingCertificateTrImg() {
		return readingCertificateTrImg;
	}

	public void setReadingCertificateTrImg(FileInfo readingCertificateTrImg) {
		this.readingCertificateTrImg = readingCertificateTrImg;
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