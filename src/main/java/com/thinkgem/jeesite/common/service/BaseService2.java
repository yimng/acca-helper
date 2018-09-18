package com.thinkgem.jeesite.common.service;

import com.thinkgem.jeesite.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public abstract class BaseService2<T> {
    @Autowired
    protected Mapper<T> mapper;

    public int save(T entity){
        return mapper.insert(entity);
    }

    public int delete(T entity){
        return mapper.deleteByPrimaryKey(entity);
    }

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
//    public List<T> selectPage(int pageNum, int pageSize){
//        PageHelper.startPage(pageNum, pageSize);
//        //Spring4支持泛型注入
//        return mapper.select(null);
//    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        //entity.setPage(page);
        page.setList(mapper.select(null));
        return page;
    }
}
