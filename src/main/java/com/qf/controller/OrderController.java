package com.qf.controller;

import com.qf.entity.Address;
import com.qf.entity.Order;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.ICartService;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @author ZouXianTao
 * @Date2019/12/26
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private IAddressService addressService;
    @Autowired
   private IOrderService orderService;
    @Autowired
    private ICartService cartService;

    /** 购物车列表点击提交，跳到订单支付页面*/
    @RequestMapping(value = "/toOrder")
    public String showOrder(HttpSession session){
        User user = (User) session.getAttribute("LOGIN_USER");
        if(user!=null){
            //去登录成功的凭证，判断是否为空，不为空，根据用户id查询用户地址集合
           List<Address> addressList=addressService.getAddressListByUid(user.getId());
            //在支付页面遍历出地址,从原来的存在session中取购物车集合和总价
            session.setAttribute("addressList",addressList);
            return "pay";
        }
      return "login";
    }
    /** 添加订单和订单详情*/
    @RequestMapping(value = "/addOrder")
    public String addOrder(Order order, HttpSession session, ModelMap map){
               //判断用户是否登录，拿到用户id
        User user = (User) session.getAttribute("LOGIN_USER");
           order.setUserid(user.getId());
        //订单id要自己生成一个流水号
        // 创建一个订单号 当前年份(4位数)+5个0-9随机数
        // 日期类
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        StringBuilder sb=new StringBuilder();
        sb.append(year);
        Random random=new Random();
        for (int i = 0; i < 5; i++) {
             sb.append(random.nextInt(10));
        }
        int id = Integer.parseInt(sb.toString());
        order.setId(id);
        //给订单表的相应的字段赋值
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        order.setOPaycount(totalPrice);
        orderService.addOrder(order,user.getId());
        //订单添加完，删除购物车
         cartService.deletCartByUid(user.getId());

       map.put("order",order);
        return "success";

    }
    /** 后端订单展示*/
    @RequestMapping(value = "/orderList")
    public String orderList(ModelMap map,HttpSession session){

        User user = (User) session.getAttribute("BACK_USER");
        if(user==null){
            return "back/backLogin";
        }
        //根据用户id查询订单集合展示并且通过一对多的级联关系查询出订单详情集合都分装在订单分页集合中
        List<Order> orderList = orderService.getOrderList(user.getId());
            map.put("orderList",orderList);

        return "back/order/orderList";
    }
}
