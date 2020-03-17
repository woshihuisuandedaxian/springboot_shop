package com.qf.service;

import com.qf.common.service.IBaseService;
import com.qf.entity.Cart;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/25
 */
public interface ICartService extends IBaseService<Cart> {

    void addCart(Cart cart);


    int getCartCounts(Integer id);


    List<Cart> getCartListByUId(Integer id);


    void deletCartByUid(Integer id);
}
