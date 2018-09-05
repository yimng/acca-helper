package com.thinkgem.jeesite.acca.api.home.entity;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * 接收的消息Entity
 * @author Young
 * @version 2016-08-15
 */
public class AppMsgPushLog extends DataEntity<AppMsgPushLog> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "消息id")
	private Long msgId;		// msg_id
	@JsonIgnore
	private Long receiveId;		// 接收人id
	@ApiModelProperty(value = "消息标题")
	private String msgTitle;		// msg_title
	@ApiModelProperty(value = "信息内容")
	private String msgContent;		// msg_content
	@ApiModelProperty(value = "消息类型，0普通消息，1系统消息")
	private Integer msgType;		// 消息类型，0普通消息，1系统消息
	@ApiModelProperty(value = "消息分组: 0 默认组(不跳转) ,1 有资有料文章")
	private Integer msgGroup;		// 消息分组：0 默认组
	@ApiModelProperty(value = "推送时间")
	private Date pushTime;		// push_time
	@ApiModelProperty(value = "消息状态：0已创建，1已推送，2推送失败")
	private Integer pushStatus;		// 消息状态：0已创建，1已推送，2推送失败
	@ApiModelProperty(value = "消息已读状态。0未读，1已读")
	private Integer readStatus;		// 消息已读状态。0未读，1已读
	@ApiModelProperty(value = "额外字段,存放消息的id,url等信息")
	private String extra;		// 额外字段，json字符串.存放有资有料信息等内容,对应转为实体传到客户端
	@ApiModelProperty(value = "消息信息,存放消息的id,url等信息")
	private AppMsgInfo msgInfo;

	public AppMsgPushLog() {
		super();
	}

	public AppMsgPushLog(String id){
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

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
		setMsgInfo(new AppMsgInfo());
	}

	//将数据转为实体
	public void setMsgInfo(AppMsgInfo msgInfo) {
		if (extra != null && !"".equals(extra)) {
			Map<String,String> map = (Map<String,String>) JSONUtils.parse(extra);
			msgInfo.setArticleId(Long.valueOf(map.get("id")));
			msgInfo.setArticleUrl(map.get("url"));
		}
		this.msgInfo = msgInfo;
	}

	public AppMsgInfo getMsgInfo() {
		return msgInfo;
	}
}