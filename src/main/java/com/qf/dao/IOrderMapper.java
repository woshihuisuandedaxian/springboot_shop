package com.qf.dao;

import com.qf.entity.Order;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IOrderMapper extends Mapper<Order> {

    void addOrder(Order order);


    List<Order> getOrderList(Integer id);
}