package com.thinkgem.jeesite.acca.web.feedback.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.web.feedback.dao.QuestionCategoryMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.QuestionCategory;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.common.service.MyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionCategoryService extends MyService<QuestionCategoryMapper, QuestionCategory> {
    @Override
    public List<QuestionCategory> findList(QuestionCategory entity) {
        return dao.selectAll();
    }

    @Override
    public PageInfo<QuestionCategory> findPage(QuestionCategory entity, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<QuestionCategory> categories = dao.selectAll();
        return new PageInfo<>(categories);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(QuestionCategory entity) {
        if (entity.getId() != null) {
            dao.updateByPrimaryKeySelective(entity);
        } else {
            dao.insert(entity);
        }
    }

    public List<QuestionCategory> findAllList() {
        return dao.selectAll();
    }
}
