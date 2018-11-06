/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.feedback.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * WebFeedbackEntity
 * @author Ivan
 * @version 2016-08-20
 */
public class WebFeedback extends DataEntity<WebFeedback> {

    private static final long serialVersionUID = 1L;
    private Long feedbackId;		// feedback_id
    private String content;		// 反馈内容
    private String phone;		// 联系方式
    private String type;
    private Byte categoryId;
    private String category;
    private Long imgId;
    private FileInfo image;
    private String img;

    public WebFeedback() {
        super();
    }

    public WebFeedback(String id){
        super(id);
    }

    @NotNull(message="feedback_id不能为空")
    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Length(min=0, max=512, message="反馈内容长度必须介于 0 和 512 之间")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public FileInfo getImage() {
        return image;
    }

    public void setImage(FileInfo image) {
        this.image = image;
    }

    public String getImg() {
        return JsonMapper.getInstance().toJson(this.image);
    }

    public void setImg(String img) {
        this.img = img;
    }
}