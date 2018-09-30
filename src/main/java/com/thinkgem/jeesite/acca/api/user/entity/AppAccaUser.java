/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * AppAccaUserEntity
 * @author Ivan
 * @version 2016-08-09
 */
public class AppAccaUser extends DataEntity<AppAccaUser> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "acca注册用户id")
	private Long accaUserId;		// acca_user_id
	@ApiModelProperty(value = "acca注册用户手机号")
	private String phone;		// phone
	@ApiModelProperty(value = "acca注册用户头像")
	private Long headId;		// 头像id
	@ApiModelProperty(value = "昵称")
	private String nickname;		// 昵称
	@ApiModelProperty(value = "用户类型：1普通用户，2学习达人，3名师")
	private Integer type;		// 用户类型：1普通用户，2学习达人，3名师
	@ApiModelProperty(value = "用户访问token")
	private String userAccessToken;		// 用户访问token
	@ApiModelProperty(value = "用户登录password")
	private String password;
	@ApiModelProperty(value = "用户状态：1正常，2冻结")
	private Integer userStatus;		// 账号状态：1正常，2冻结
	@JsonIgnore
	private String provinceName;		// 定位省份名称
	@JsonIgnore
	private Integer provinceId;		// 省份id
	@ApiModelProperty(value = "城市名称")
	private String cityName;		// 定位城市名称
	@ApiModelProperty(value = "城市id")
	private Integer cityId;		// 城市id
	@ApiModelProperty(value = "设备id")
	private String deviceId;		// 设备id
	@JsonIgnore
	private Date loginDate;		// 登陆时间
	@ApiModelProperty(value = "注册acca的真是姓名")
	private String registerName;		// 注册acca的真是姓名
	@ApiModelProperty(value = "身份证件号码")
	private String registerCardNumber;		// 身份证号码
	@ApiModelProperty(value = "acca注册手机号")
	private String registerPhone;		// acca注册手机号
	@ApiModelProperty(value = "acca注册邮箱")
	private String registerEmail;		// acca注册邮箱
	@ApiModelProperty(value = "acca注册用2寸白底彩色照片id")
	private Long registerWhiteColorImgId;		// acca注册用2寸白底彩色照片id
	@ApiModelProperty(value = "acca注册账号")
	private String accaRegisterName;		// acca注册账号
	@ApiModelProperty(value = "acca注册密码")
	private String accaRegisterPassword;		// acca密码
	@ApiModelProperty(value = "用户头像")
	private FileInfo headImg;
	@ApiModelProperty(value = "acca注册用2寸白底彩色照片")
	private FileInfo registerWhiteColorImg;
	@ApiModelProperty(value = "ACCA小助手，随身陪伴的ACCA中文学习工具！")
	private String examDesc="ACCA小助手，帖身陪伴的ACCA中文学习工具！";
	@ApiModelProperty(value = "身份证件类型：1身份证，2护照")
	private Integer cardType;
	@ApiModelProperty(value = "身份类型：1在校生，2毕业生，3国外院校毕业生")
	private Integer identityType;
	@ApiModelProperty(value = "学校/单位")
	private String org;		//学校/单位
	@ApiModelProperty(value = "是否可编辑：1可以编辑，2不可以编辑")
	private int editable=2;
	@ApiModelProperty(value = "是否中博学员：0非中博学员，1提交认证,2认证未通过，3，中博学员")
	private int iszbg;
	@ApiModelProperty(value = "报名班型")
	private String classStyle;
	@ApiModelProperty(value = "年级")
	private String grade;
	
	public int getIszbg() {
		return iszbg;
	}

	public void setIszbg(int iszbg) {
		this.iszbg = iszbg;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public AppAccaUser() {
		super();
	}

	public AppAccaUser(String id){
		super(id);
	}
	
	public AppAccaUser(Long accaUserId){
		this.accaUserId = accaUserId;
	}

	@NotNull(message="acca_user_id不能为空")
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	@Length(min=0, max=32, message="phone长度必须介于 0 和 32 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Long getHeadId() {
		return headId;
	}

	public void setHeadId(Long headId) {
		this.headId = headId;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=4, message="用户类型：1普通用户，2学习达人，3名师长度必须介于 0 和 4 之间")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="用户访问token长度必须介于 0 和 64 之间")
	public String getUserAccessToken() {
		return userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}
	
	@NotNull(message="账号状态：1正常，2冻结不能为空")
	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	@Length(min=0, max=64, message="定位省份名称长度必须介于 0 和 64 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=32, message="省份id长度必须介于 0 和 32 之间")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
	@Length(min=0, max=64, message="定位城市名称长度必须介于 0 和 64 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=64, message="设备id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=0, max=64, message="注册acca的真是姓名长度必须介于 0 和 64 之间")
	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	
	@Length(min=0, max=64, message="身份证号码长度必须介于 0 和 64 之间")
	public String getRegisterCardNumber() {
		return registerCardNumber;
	}

	public void setRegisterCardNumber(String registerCardNumber) {
		this.registerCardNumber = registerCardNumber;
	}
	
	@Length(min=0, max=32, message="acca注册手机号长度必须介于 0 和 32 之间")
	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}
	
	@Length(min=0, max=64, message="acca注册邮箱长度必须介于 0 和 64 之间")
	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	
	public Long getRegisterWhiteColorImgId() {
		return registerWhiteColorImgId;
	}

	public void setRegisterWhiteColorImgId(Long registerWhiteColorImgId) {
		this.registerWhiteColorImgId = registerWhiteColorImgId;
	}
	
	@Length(min=0, max=64, message="acca注册账号长度必须介于 0 和 64 之间")
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

	public FileInfo getHeadImg() {
		return headImg;
	}

	public void setHeadImg(FileInfo headImg) {
		this.headImg = headImg;
	}

	public FileInfo getRegisterWhiteColorImg() {
		return registerWhiteColorImg;
	}

	public void setRegisterWhiteColorImg(FileInfo registerWhiteColorImg) {
		this.registerWhiteColorImg = registerWhiteColorImg;
	}

	public String getExamDesc() {
		return examDesc;
	}

	public void setExamDesc(String examDesc) {
		this.examDesc = examDesc;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}