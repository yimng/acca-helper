package com.thinkgem.jeesite.acca.web.feedback.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.api.exam.dao.AppExamPlaceDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppSmallExamPlace;
import com.thinkgem.jeesite.acca.api.model.request.ExamCenterReq;
import com.thinkgem.jeesite.acca.web.feedback.dao.ExamCenterQuestionMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.ExamCenterQuestion;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.service.MyService;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_DELETE;
import static com.thinkgem.jeesite.common.persistence.BaseEntity.DEL_FLAG_NORMAL;

@Service
@Transactional(readOnly = true)
public class ExamCenterQuestionService extends MyService<ExamCenterQuestionMapper, ExamCenterQuestion> {
    @Autowired
    private ExamCenterAnswerService answerService;
    @Autowired
    private AppExamPlaceDao placeDao;
    @Override
    public List<ExamCenterQuestion> findList(ExamCenterQuestion entity) {
        return null;
    }

    @Override
    public PageInfo<ExamCenterQuestion> findPage(ExamCenterQuestion entity, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(ExamCenterQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", DEL_FLAG_NORMAL);
        if (entity.getTitile() != null) {
            criteria.andLike("titile", "%" + entity.getTitile() + "%");
        }
        if (entity.getExamPlaceName() != null) {
            criteria.andLike("examPlaceName", "%" + entity.getExamPlaceName() + "%");
        }

        List<ExamCenterQuestion> examCenterQuestions = dao.selectByExample(example);
        return new PageInfo<>(examCenterQuestions);
    }

    public BasePageResponse<AppSmallExamPlace> getExamCenterList(ExamCenterReq  req, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<AppSmallExamPlace> examCenterList = placeDao.getExamCenterList(req.getExamCityId());
        return new BasePageResponse<>(examCenterList);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(ExamCenterQuestion entity) {
        dao.insert(entity);
    }

    public ExamCenterQuestion get(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int delete(Long id) {
        answerService.deleteByQuestionId(id);
        ExamCenterQuestion question = new ExamCenterQuestion();
        question.setDelFlag(DEL_FLAG_DELETE);
        question.setId(id);
        return dao.updateByPrimaryKeySelective(question);
    }
}
