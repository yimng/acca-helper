/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 推送消息Entity
 * @author Young
 * @version 2016-08-25
 */
public class WebMsgPushLog extends DataEntity<WebMsgPushLog> {
	
	private static final long serialVersionUID = 1L;
	private Long msgId;		// msg_id
	private Long receiveId;		// 接收人id
	private String msgTitle;		// msg_title
	private String msgContent;		// msg_content
	private Integer msgType;		// 消息类型，0普通消息，1系统消息
	private Integer msgGroup;		// 消息分组：0 默认组
	private Date pushTime;		// push_time
	private Integer pushStatus;		// 消息状态：0已创建，1已推送，2推送失败
	private Integer readStatus;		// 消息已读状态。0未读，1已读
	private String extra;		// 额外字段，json字符串
	
	public WebMsgPushLog() {
		super();
	}

	public WebMsgPushLog(String id){
		super(id);
	}

	@NotNull(message="msg_id不能为空")
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	
	@NotNull(message="接收人id不能为空")
	public Long getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}
	
	@Length(min=0, max=256, message="msg_title长度必须介于 0 和 256 之间")
	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@NotNull(message="消息类型，0普通消息，1系统消息不能为空")
	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	
	@NotNull(message="消息分组：0 默认组不能为空")
	public Integer getMsgGroup() {
		return msgGroup;
	}

	public void setMsgGroup(Integer msgGroup) {
		this.msgGroup = msgGroup;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
	@NotNull(message="消息状态：0已创建，1已推送，2推送失败不能为空")
	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	
	@NotNull(message="消息已读状态。0未读，1已读不能为空")
	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}
	
	@Length(min=0, max=255, message="额外字段，json字符串长度必须介于 0 和 255 之间")
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
}