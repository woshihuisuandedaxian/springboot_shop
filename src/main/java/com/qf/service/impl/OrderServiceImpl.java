package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.IOrderMapper;
import com.qf.entity.Order;
import com.qf.service.IOrderDetailService;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/27
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements IOrderService {
    @Autowired
    private IOrderMapper orderMapper;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Override
    protected Mapper<Order> getMapper() {
        return orderMapper;
    }
    /** 添加订单*/
    @Override
    public void addOrder(Order order,Integer uid) {
        //添加订单
        orderMapper.addOrder(order);
        //添加订单详情
        orderDetailService.addOrderDetail(order.getId(),uid);

    }
   /** 根据用户id查询订单集合*/
    @Override
    public List<Order> getOrderList( Integer id) {
        return orderMapper.getOrderList(id);

    }



}
