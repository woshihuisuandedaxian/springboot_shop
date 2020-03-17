package com.qf.common.utils;

/**
 * @author ZouXianTao
 * @Date2019/12/25
 */

import com.qf.entity.Cart;

import javax.servlet.http.HttpSession;

/** 跟购物车有关的工具类*/
public class CartUtils {
    // 提供一个方法供外界去调用，得到一辆购物车
    public static Cart getShopCar(HttpSession session) {
        // 首先看下session域中是否有购物车，如果有就从域中取，如果没有，才new一辆
        Cart shopCar = (Cart) session.getAttribute("SHOP_CAR");
        if (shopCar == null) {
            shopCar = new Cart();
            // 放到session域中
            session.setAttribute("SHOP_CAR", shopCar);
        }
        return shopCar;
    }
}
