package com.thinkgem.jeesite.acca.web.feedback.dao;

import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.common.persistence.MyMapper;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

@MyBatisDao
public interface QuestionMapper extends MyMapper<Question> {
    List<Question> findList(Question q);
    Question getDetail(Long id);
}