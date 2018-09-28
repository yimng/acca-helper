package com.thinkgem.jeesite.acca.web.feedback.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tbl_question_praise")
public class QuestionPraise implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "acca_user_id")
    private Long accaUserId;

    /**
     * 0.有用 1. 内容太啰嗦2. 答案不清楚 3. 操作示解决 4. 其它问题
     */
    @Column(name = "praise_flag")
    private Short praiseFlag;

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
     * @return question_id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * @return acca_user_id
     */
    public Long getAccaUserId() {
        return accaUserId;
    }

    /**
     * @param accaUserId
     */
    public void setAccaUserId(Long accaUserId) {
        this.accaUserId = accaUserId;
    }

    /**
     * 获取0.有用 1. 内容太啰嗦2. 答案不清楚 3. 操作示解决 4. 其它问题
     *
     * @return praise_flag - 0.有用 1. 内容太啰嗦2. 答案不清楚 3. 操作示解决 4. 其它问题
     */
    public Short getPraiseFlag() {
        return praiseFlag;
    }

    /**
     * 设置0.有用 1. 内容太啰嗦2. 答案不清楚 3. 操作示解决 4. 其它问题
     *
     * @param praiseFlag 0.有用 1. 内容太啰嗦2. 答案不清楚 3. 操作示解决 4. 其它问题
     */
    public void setPraiseFlag(Short praiseFlag) {
        this.praiseFlag = praiseFlag;
    }
}