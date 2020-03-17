package com.qf.service;

import com.qf.common.service.IBaseService;
import com.qf.entity.Order;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/27
 */
public interface IOrderService extends IBaseService<Order> {

    void addOrder(Order order, Integer id);


    List<Order> getOrderList(Integer id);
}
