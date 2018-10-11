package com.thinkgem.jeesite.acca.api.user.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.web.feedback.dao.QuestionMapper;
import com.thinkgem.jeesite.acca.web.feedback.dao.QuestionPraiseMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.acca.web.feedback.entity.QuestionPraise;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AppQuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionPraiseMapper questionPraiseMapper;

    public BaseObjResponse<Question> getDetail(Long id) {
        Question detail = questionMapper.getDetail(id);
        return new BaseObjResponse<>(detail);
    }

    public BasePageResponse<Question> getQuestionsByCategory(int pageNo, int pageSize, Byte id) {
        Question question = new Question();
        question.setCatergoryId(id);
        PageHelper.startPage(pageNo, pageSize);
        List<Question> list = questionMapper.findList(question);
        return new BasePageResponse<>(list);
    }

    public BasePageResponse<Question> getHotQuestions(int pageNo, int pageSize, Boolean hot) {
        Question question = new Question();
        question.setHot(hot);
        PageHelper.startPage(pageNo, pageSize);
        List<Question> list = questionMapper.findList(question);
        return new BasePageResponse<>(list);
    }

    public BasePageResponse<Question> searchQuestions(int pageNo, int pageSize, String title) {
        Question question = new Question();
        question.setTitle(title);
        PageHelper.startPage(pageNo, pageSize);
        List<Question> list = questionMapper.findList(question);
        return new BasePageResponse<>(list);
    }

    public BaseObjResponse<Question> praiseQuestion(Long questionId, Short praise) {
        QuestionPraise questionPraise =  new QuestionPraise();
        questionPraise.setId(questionId);
        questionPraise.setPraiseFlag(praise);
        questionPraiseMapper.updateByPrimaryKeySelective(questionPraise);
        Question detail = questionMapper.getDetail(questionId);
        return new BaseObjResponse<>(detail);
    }
}
