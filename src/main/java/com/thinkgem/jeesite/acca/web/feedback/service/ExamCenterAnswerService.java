package com.thinkgem.jeesite.acca.web.feedback.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.web.feedback.dao.ExamCenterAnswerMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.ExamCenterAnswer;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.service.MyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_DELETE;
import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_NORMAL;

@Service
@Transactional(readOnly = true)
public class ExamCenterAnswerService extends MyService<ExamCenterAnswerMapper, ExamCenterAnswer> {

    @Override
    public List<ExamCenterAnswer> findList(ExamCenterAnswer entity) {
        return null;
    }

    @Override
    public PageInfo<ExamCenterAnswer> findPage(ExamCenterAnswer entity, int pageNo, int pageSize) {
        Example example = new Example(ExamCenterAnswer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DEL_FLAG_NORMAL);
        criteria.andEqualTo("questionId", entity.getQuestionId());
        PageHelper.startPage(pageNo, pageSize);
        List<ExamCenterAnswer> answers = dao.selectByExample(example);
        return new PageInfo<>(answers);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(ExamCenterAnswer entity) {
        dao.insert(entity);
    }

    @Transactional(readOnly = false)
    public int deleteByQuestionId(Long id) {
        Example example = new Example(ExamCenterAnswer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("questionId", id);
        ExamCenterAnswer answer = new ExamCenterAnswer();
        answer.setDelFlag(DEL_FLAG_DELETE);
        return dao.updateByExampleSelective(answer, example);
    }

    @Transactional(readOnly = false)
    public int delete(Long id) {
        ExamCenterAnswer answer = new ExamCenterAnswer();
        answer.setId(id);
        answer.setDelFlag(DEL_FLAG_DELETE);
        return dao.updateByPrimaryKeySelective(answer);
    }
}
