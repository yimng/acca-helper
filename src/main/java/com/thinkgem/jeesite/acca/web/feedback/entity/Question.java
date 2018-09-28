package com.thinkgem.jeesite.acca.web.feedback.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "tbl_question")
public class Question implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 问题名称
     */
    private String title;

    /**
     * 问题分类
     */
    @Column(name = "catergory_id")
    private Byte catergoryId;

    /**
     * 是否热门
     */
    private Boolean hot;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 问题回答
     */
    private String answer;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取问题名称
     *
     * @return title - 问题名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置问题名称
     *
     * @param title 问题名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取问题分类
     *
     * @return catergory_id - 问题分类
     */
    public Byte getCatergoryId() {
        return catergoryId;
    }

    /**
     * 设置问题分类
     *
     * @param catergoryId 问题分类
     */
    public void setCatergoryId(Byte catergoryId) {
        this.catergoryId = catergoryId;
    }

    /**
     * 获取是否热门
     *
     * @return hot - 是否热门
     */
    public Boolean getHot() {
        return hot;
    }

    /**
     * 设置是否热门
     *
     * @param hot 是否热门
     */
    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    /**
     * 获取发布人
     *
     * @return publisher - 发布人
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置发布人
     *
     * @param publisher 发布人
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获取发布时间
     *
     * @return publish_time - 发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishTime 发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取问题回答
     *
     * @return answer - 问题回答
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置问题回答
     *
     * @param answer 问题回答
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Transient
    private Date start;
    @Transient
    private Date end;
    @Transient
    private String category;
    @Transient
    private String publisherName;
    @Transient
    private String modifierName;
    @Transient
    private int praised;
    @Transient
    private int disPraised;

}