package com.thinkgem.jeesite.common.service;

import com.thinkgem.jeesite.common.persistence.MyMapper;
import com.thinkgem.jeesite.common.persistence.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class MyService<D extends MyMapper<T>, T> {

    @Autowired
    protected D dao;

    public T get(String id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.selectOne(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public abstract List<T> findList(T entity);

    public abstract PageInfo<T> findPage(T entity, int pageNo, int pageSize);

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    public abstract void save(T entity);

    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }
}
