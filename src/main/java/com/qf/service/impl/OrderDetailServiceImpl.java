package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.IOrderDetailMapper;
import com.qf.entity.Cart;
import com.qf.entity.GoodsInfo;
import com.qf.entity.OrderDetail;
import com.qf.service.ICartService;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/27
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements IOrderDetailService{
    @Autowired
    private IOrderDetailMapper orderDetailMapper;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Override
    protected Mapper<OrderDetail> getMapper() {
        return orderDetailMapper;
    }
  /** 添加订单详情*/
    @Override
    public void addOrderDetail(Integer oid, Integer uid) {
       //通过用户id查询购物车集合，一辆购物车就是一张订单详情表
        List<Cart> cartList = cartService.getCartListByUId(uid);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for (Cart cart : cartList) {
            //一辆购物车就有一个商品对象
            GoodsInfo goods = goodsInfoService.getById(cart.getGid());
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setGoodsTotalPrice(cart.getSubTotal());
            orderDetail.setGoodsnum(cart.getNum());
            orderDetail.setGoodsDescription(goods.getGoodsDescription());
            orderDetail.setGoodsid(goods.getId());
            orderDetail.setGoodsname(goods.getGoodsName());
            orderDetail.setGoodspic(goods.getGoodsPic());
            orderDetail.setGoodsprice(goods.getGoodsPrice());
            orderDetail.setOOrderid(oid);
            orderDetailList.add(orderDetail);
        }
        orderDetailMapper.addOrderDetail(orderDetailList);

    }
}
