package com.qf.dao;

import com.qf.entity.Cart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ICartMapper extends Mapper<Cart> {

    List<Cart> getCartListByUId(Integer id);

    Cart getCartByGid(Integer goodId);
}