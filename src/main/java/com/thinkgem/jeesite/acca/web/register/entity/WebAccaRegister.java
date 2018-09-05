package com.thinkgem.jeesite.acca.web.register.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 代注册Entity
 * @author Young
 * @version 2016-08-17
 */
public class WebAccaRegister extends DataEntity<WebAccaRegister> {
	
	private static final long serialVersionUID = 1L;
	private Long accaRegisterId;		// acca_register_id
	private Long accaUserId;		// acca注册用户id
	private Integer registerType;		// 注册类型：1acca注册，2fia注册
	private String name;		// 姓名
	private Integer sex;		// 性别：1男，2女
	private Integer cardType;		// 证件类型:1.身份证;2.护照
	private String cardNumber;		// 身份证号码
	private String phone;		// 手机号
	private String email;		// 邮箱
	private String cityId;		// 距离最近城市id
	private Integer identityType;		// 身份类型：1在校生，2毕业生，3国外院校毕业生
	private Long whiteColorImgId;		// 2寸白底彩色照片
	private Long identityCardOgImgId;		// 身份证明原件id
	private Long identityCardTrImgId;		// 身份证明翻译件
	private Long readingCertificateImgId;		// 在读证明图片id
	private Long readingCertificateTrImgId;		// 在读证明翻译件图片id
	private Long examsResultOgImgId;		// 成绩单原件图片id
	private Long examsResultTrImgId;		// 成绩单翻译件图片id
	private Long graduateCertifcateOgImgId;		// 毕业证书或学位证书原件图片id
	private Long graduateCertifcateTrImgId;		// 毕业证书或学位证书翻译件图片id
	private Integer status;		// 状态：1待付款，2待审核，3审核通过，4审核不通过，5注册成功
	private BigDecimal payAmout;		// 注册缴纳费用，如900.00元
	private Long payImgId;		// 支付凭证截图id
	private String accaRegisterName;		// acca学院号
	private String accaRegisterPassword;		// acca密码
	private String checkPersonId;		// 审核人id
	private String checkPersonName;		// 审核人姓名
	private Date checkTime;		// 审核时间
	private String checkReason;		// 审核不通过原因
	private String cityName;  //城市名称
	private String readingSchool;	//在校生学校
	private String readingMajor;	//在校生专业
	private String readingGrade;	//在校生年级

	private FileInfo whiteColorImg;
	private String whiteColorImgStr;
	public String getWhiteColorImgStr(){
		return JsonMapper.getInstance().toJson(this.whiteColorImg);
	}

	private FileInfo identityCardOgImg;
	private String identityCardOgImgStr;
	public String getIdentityCardOgImgStr(){
		return JsonMapper.getInstance().toJson(this.identityCardOgImg);
	}

	private FileInfo identityCardTrImg;
	private String identityCardTrImgStr;
	public String getIdentityCardTrImgStr(){
		return JsonMapper.getInstance().toJson(this.identityCardTrImg);
	}

	private FileInfo readingCertificateImg;
	private String readingCertificateImgStr;
	public String getReadingCertificateImgStr(){
		return JsonMapper.getInstance().toJson(this.readingCertificateImg);
	}

	private FileInfo readingCertificateTrImg;
	private String readingCertificateTrImgStr;
	public String getReadingCertificateTrImgStr(){
		return JsonMapper.getInstance().toJson(this.readingCertificateTrImg);
	}

	private FileInfo examsResultOgImg;
	private String examsResultOgImgStr;
	public String getExamsResultOgImgStr(){
		return JsonMapper.getInstance().toJson(this.examsResultOgImg);
	}

	private FileInfo examsResultTrImg;
	private String examsResultTrImgStr;
	public String getExamsResultTrImgStr(){
		return JsonMapper.getInstance().toJson(this.examsResultTrImg);
	}

	private FileInfo graduateCertifcateOgImg;
	private String graduateCertifcateOgImgStr;
	public String getGraduateCertifcateOgImgStr(){
		return JsonMapper.getInstance().toJson(this.graduateCertifcateOgImg);
	}

	private FileInfo graduateCertifcateTrImg;
	private String graduateCertifcateTrImgStr;
	public String getGraduateCertifcateTrImgStr(){
		return JsonMapper.getInstance().toJson(this.graduateCertifcateTrImg);
	}

	private FileInfo payImg;
	private String payImgStr;
	public String getPayImgStr(){
		return JsonMapper.getInstance().toJson(this.payImg);
	}

	
	public WebAccaRegister() {
		super();
	}

	public WebAccaRegister(String id){
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
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonBackReference
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
	
	@Length(min=0, max=64, message="审核人id长度必须介于 0 和 64 之间")
	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}
	
	@Length(min=0, max=64, message="审核人姓名长度必须介于 0 和 64 之间")
	public String getCheckPersonName() {
		return checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	@Length(min=0, max=512, message="审核不通过原因长度必须介于 0 和 512 之间")
	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
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