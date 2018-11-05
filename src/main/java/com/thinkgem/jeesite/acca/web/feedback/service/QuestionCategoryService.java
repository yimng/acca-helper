package com.thinkgem.jeesite.acca.web.feedback.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.web.feedback.dao.QuestionCategoryMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.QuestionCategory;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.common.service.MyService;

import java.util.List;

@MyBatisDao
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
    public void save(QuestionCategory entity) {

    }

    public List<QuestionCategory> findAllList() {
        return dao.selectAll();
    }
}
