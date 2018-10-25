package com.thinkgem.jeesite.acca.web.feedback.service;

import com.github.pagehelper.PageHelper;
import com.thinkgem.jeesite.acca.web.feedback.dao.QuestionMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.Question;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.service.MyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionService extends MyService<QuestionMapper, Question> {

    @Autowired
    private QuestionMapper mapper;

    public Question get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Question> findList(Question entity) {
        return null;
    }

    @Override
        public PageInfo<Question> findPage(Question entity, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo(mapper.findList(entity));
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Question entity) {
        User user = UserUtils.getUser();
        if (entity.getId() != null) {
            entity.setModifier(user.getId());
            entity.setModifyTime(new Date());
            mapper.updateByPrimaryKeySelective(entity);
        } else {
            entity.setPublisher(user.getId());
            entity.setPublishTime(new Date());
            mapper.insert(entity);
        }
    }
}
