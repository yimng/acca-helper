package com.thinkgem.jeesite.acca.api.home.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Young
 * @version 2016/8/20
 */
public class AppMsgInfo {

    private static final long serialVersionUID = 1L;

    //有资有料请求数据
    @ApiModelProperty(value = "文章id")
    private Long articleId;		// articleId
    @ApiModelProperty(value = "文章url")
    private String articleUrl;		// articleUrl

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
