package com.qf.dao;

import com.qf.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IOrderDetailMapper extends Mapper<OrderDetail> {


    void addOrderDetail( @Param("list") List<OrderDetail> orderDetailList);
}