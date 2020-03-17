package com.qf.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.service.IBaseService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    protected abstract Mapper<T> getMapper();
    /** 查询集合*/
    @Override
    public List<T> getList() {
        return getMapper().select(null);
    }
  /** 添加对象*/
    @Override
    public int add(T t) {
        return getMapper().insert(t);
    }
  /** 修改*/
    @Override
    public int update(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }
  /** 根据用户id查询对象*/
    @Override
    public T getById(Integer id) {
        return getMapper().selectByPrimaryKey(id);
    }
   /** 根据id删除对象*/
    @Override
    public int deleteById(Integer id) {
        return getMapper().deleteByPrimaryKey(id);
    }
   /** 分页查询*/
    @Override
    public PageInfo<T> getPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<T> list=getMapper().select(null);
        return new PageInfo<T>(list);
    }
}
