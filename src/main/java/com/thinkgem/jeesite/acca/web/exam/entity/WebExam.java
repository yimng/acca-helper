/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 考试类Entity
 * @author Michael
 * @version 2016-08-24
 */
public class WebExam extends DataEntity<WebExam> {
	
	private static final long serialVersionUID = 1L;
	private Long examId;		// exam_id
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考
	private String examCityId;		// exam_city_id
	private String examCityName;		// exam_city_name
	private Long examPlaceId;		// 考点id
	private String examPlaceName;		// 考点名称
	private Date examStartTime;		// 考试开始时间
	private Date examEndTime;		// 考试结束时间
	private Date examSignupEndtime;		// 报名截止时间
	private Integer totalSeats;		// 总席位数
	private Integer usedSeats;		// 已经被占用的席位数
	
	private Integer examFlag;
	private Long signupNum;
	private List<SmallCourse> courses;
//	private String timeStr;
	private String courseStr;
	private Integer examSignupStatus;
	private Long examCourseId;
	private String examSeasonStr;
	private String examVersionJson;
	private List<WebExam> list;
	private Long oldExamPlaceId;
	private Date oldExamStartTime;
	
	private Integer self = Constants.ExamType.self;
	private Integer mac = Constants.ExamType.officialM;
	private Integer pen = Constants.ExamType.officialPen;
	private Integer now = Constants.ExamFlag.now;
	private Integer old = Constants.ExamFlag.old;
	private Map<Integer, String> orderMaps = Constants.ORDER_STATUS_TYPES;
	
	private String start;
	private String end;
	private Integer examType2;

	//考点id列表
	private List<WebExamPlace> places;
	
	public WebExam() {
		super();
	}

	public WebExam(String id){
		super(id);
	}

	@NotNull(message="exam_id不能为空")
	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
	
	@Length(min=0, max=11, message="支持的考试类型：1自有考试，2官方机考，4官方笔考长度必须介于 0 和 11 之间")
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	
	@Length(min=0, max=11, message="exam_city_id长度必须介于 0 和 11 之间")
	public String getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(String examCityId) {
		this.examCityId = examCityId;
	}
	
	@Length(min=0, max=64, message="exam_city_name长度必须介于 0 和 64 之间")
	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
	}
	
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}
	
	@Length(min=0, max=255, message="考点名称长度必须介于 0 和 255 之间")
	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(Date examStartTime) {
		if(this.examStartTime != null){
			this.examSeasonStr = TimeUtils.DateToStr(examStartTime, TimeUtils.dateFormat3);
		}
		this.examStartTime = examStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(Date examEndTime) {
		this.examEndTime = examEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamSignupEndtime() {
		return examSignupEndtime;
	}

	public void setExamSignupEndtime(Date examSignupEndtime) {
		this.examSignupEndtime = examSignupEndtime;
	}
	
	@Length(min=0, max=11, message="总席位数长度必须介于 0 和 11 之间")
	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	@Length(min=1, max=11, message="已经被占用的席位数长度必须介于 1 和 11 之间")
	public Integer getUsedSeats() {
		return usedSeats;
	}

	public void setUsedSeats(Integer usedSeats) {
		this.usedSeats = usedSeats;
	}

	public Long getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(Long signupNum) {
		this.signupNum = signupNum;
	}

	public List<SmallCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<SmallCourse> courses) {
		this.courses = courses;
	}

	public Integer getExamFlag() {
		return examFlag;
	}

	public void setExamFlag(Integer examFlag) {
		this.examFlag = examFlag;
	}

	public Integer getSelf() {
		return self;
	}

	public void setSelf(Integer self) {
		this.self = self;
	}

	public Integer getMac() {
		return mac;
	}

	public void setMac(Integer mac) {
		this.mac = mac;
	}

	public Integer getPen() {
		return pen;
	}

	public void setPen(Integer pen) {
		this.pen = pen;
	}

//	public String getTimeStr() {
//		this.timeStr = TimeUtils.DateToStr(this.examStartTime, TimeUtils.dateFormat1)+"-"+TimeUtils.DateToStr(this.examEndTime, TimeUtils.dateFormat2);
//		return this.timeStr;
//	}
//
//	public void setTimeStr(String timeStr) {
//		this.timeStr = timeStr;
//	}

	public String getCourseStr() {
		if(this.courses != null){
			StringBuilder sb = new StringBuilder();
			for(SmallCourse sc : courses){
				if(sc.getCourse() != null){
					List<WebExamVersion> list = sc.getExamVersions();
					StringBuilder s = new StringBuilder();
					if(list != null && list.size() != 0){
						s.append("(");
						for(WebExamVersion we : list){
							s.append(we.getExamVersionName() + "、");
						}
						sb.append(sc.getCourse() + s.substring(0, s.length() - 1) + ")" + "/");
					} else {
						sb.append(sc.getCourse() + "/");
					}
				}
			}
			return sb.length() == 0 ? null : sb.substring(0, sb.length() - 1);
		}
		return courseStr;
	}

	public void setCourseStr(String courseStr) {
		this.courseStr = courseStr;
	}

	public Integer getNow() {
		return now;
	}

	public void setNow(Integer now) {
		this.now = now;
	}

	public Integer getOld() {
		return old;
	}

	public void setOld(Integer old) {
		this.old = old;
	}

	public Integer getExamSignupStatus() {
		return examSignupStatus;
	}

	public void setExamSignupStatus(Integer examSignupStatus) {
		this.examSignupStatus = examSignupStatus;
	}

	public Map<Integer, String> getOrderMaps() {
		return orderMaps;
	}

	public void setOrderMaps(Map<Integer, String> orderMaps) {
		this.orderMaps = orderMaps;
	}

	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}

	public String getExamSeasonStr() {
		return examSeasonStr;
	}

	public void setExamSeasonStr(String examSeasonStr) {
		this.examSeasonStr = examSeasonStr;
	}

	public String getExamVersionJson() {
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		this.examVersionJson = examVersionJson;
	}

	public List<WebExam> getList() {
		return list;
	}

	public void setList(List<WebExam> list) {
		this.list = list;
	}

	public Date getOldExamStartTime() {
		return oldExamStartTime;
	}

	public void setOldExamStartTime(Date oldExamStartTime) {
		this.oldExamStartTime = oldExamStartTime;
	}

	public Long getOldExamPlaceId() {
		return oldExamPlaceId;
	}

	public void setOldExamPlaceId(Long oldExamPlaceId) {
		this.oldExamPlaceId = oldExamPlaceId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getExamType2() {
		return examType2;
	}

	public void setExamType2(Integer examType2) {
		this.examType2 = examType2;
	}

	public List<WebExamPlace> getPlaces() {
		return places;
	}

	public void setPlaces(List<WebExamPlace> places) {
		this.places = places;
	}
}