package com.thinkgem.jeesite.common.persistence;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
