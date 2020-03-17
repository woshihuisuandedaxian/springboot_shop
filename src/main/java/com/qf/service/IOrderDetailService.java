package com.qf.service;

import com.qf.common.service.IBaseService;
import com.qf.entity.OrderDetail;

/**
 * @author ZouXianTao
 * @Date2019/12/27
 */
public interface IOrderDetailService extends IBaseService<OrderDetail> {

    void addOrderDetail(Integer id, Integer uid);
}
