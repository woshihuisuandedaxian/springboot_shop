package com.qf.common.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
public interface IBaseService<T>{
    /** 查询集合*/
    public List<T> getList();
    /** 新增*/
    public int add(T t);
    /** 修改*/
    public int update(T t);
    /** 通过id查询对象*/
    public T getById(Integer id);
    /** 通过id删除对象*/
    public int deleteById(Integer id);
    /** 分页查询*/
    public PageInfo<T> getPage(Integer pageNum,Integer pageSize);


}
