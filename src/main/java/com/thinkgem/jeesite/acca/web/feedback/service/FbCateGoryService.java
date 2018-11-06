package com.thinkgem.jeesite.acca.web.feedback.service;

import com.thinkgem.jeesite.acca.web.feedback.dao.FbCategoryMapper;
import com.thinkgem.jeesite.acca.web.feedback.entity.FbCategory;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import com.thinkgem.jeesite.common.service.MyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FbCateGoryService extends MyService<FbCategoryMapper, FbCategory> {
    @Override
    public List<FbCategory> findList(FbCategory entity) {
        return dao.selectAll();
    }

    @Override
    public PageInfo<FbCategory> findPage(FbCategory entity, int pageNo, int pageSize) {
        return new PageInfo<>(dao.selectAll());
    }

    @Override
    @Transactional(readOnly = false)
    public void save(FbCategory entity) {
        if (entity.getId() != null) {
            dao.updateByPrimaryKeySelective(entity);
        } else {
            dao.insert(entity);
        }
    }

    public List<FbCategory> findAllList() {
        return dao.selectAll();
    }

    @Override
    public FbCategory get(String id) {
        return dao.selectByPrimaryKey(id);
    }
}
