package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.ICartMapper;
import com.qf.entity.Cart;
import com.qf.entity.GoodsInfo;
import com.qf.service.ICartService;
import com.qf.service.IGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/25
 */
@Service
public class CartServiceImpl extends BaseServiceImpl<Cart> implements ICartService {
    @Autowired
    private ICartMapper cartMapper;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Override
    protected Mapper<Cart> getMapper() {
        return cartMapper;
    }

  /** 用户登录了，添加商品到购物车*/
    @Override
    public void addCart(Cart cart) {

         //根据商品id查询商品详情
        GoodsInfo goods = goodsInfoService.getById(cart.getGid());
        cart.setGoodsInfo(goods);
        //算商品的小计
        cart.setSubTotal(cart.getNum()*(goods.getGoodsPrice()));
        //添加到购物车中
        cartMapper.insert(cart);
    }
  /** 查询购物车的数量*/
    @Override
    public int getCartCounts(Integer id) {

        Cart cart=new Cart();
        cart.setUid(id);
        return cartMapper.selectCount(cart);

    }

    @Override
    public List<Cart> getCartListByUId(Integer id) {
        List<Cart> cartList = cartMapper.getCartListByUId(id);
        for (Cart cart : cartList) {
            //根据商品id查询出商品对象
            GoodsInfo goods = goodsInfoService.getById(cart.getGid());
            cart.setGoodsInfo(goods);


        }
        return cartList;
    }
  /** 根据用户id删除购物车*/
    @Override
    public void deletCartByUid(Integer id) {
         Cart cart=new Cart();
         cart.setUid(id);
         cartMapper.delete(cart);
    }


}
