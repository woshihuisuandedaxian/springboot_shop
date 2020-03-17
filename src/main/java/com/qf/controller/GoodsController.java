package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.common.entity.Page;
import com.qf.entity.Cart;
import com.qf.entity.GoodsInfo;
import com.qf.entity.GoodsType;
import com.qf.entity.User;
import com.qf.service.ICartService;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
@RequestMapping(value = "/goods")
@Controller
public class GoodsController {
    @Autowired
    private IGoodsTypeService goodsTypeService;
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private ICartService cartService;
    /** 点击首页，查找商品类别信息和商品详情*/
    @RequestMapping(value = "/selectGoods")
    public String selectGoods(ModelMap map){
        //查询商品类别
        List<GoodsType> gtList = goodsTypeService.getList();
        //查询商品详情
        List<GoodsInfo> giList = goodsInfoService.getList();
        map.put("gtList",gtList);
        map.put("giList",giList);
        return "index";
    }
    /** 点击商品查询单个商品的详情并跳转到商品详情页面*/
    @RequestMapping(value = "/selectGoodsById/{id}")
    public String selectGoodsById(@PathVariable("id")Integer id,ModelMap map){
        GoodsInfo goods = goodsInfoService.getById(id);
         map.put("good",goods);
        return "introduction";
    }
    /** 添加购物车*/
    @RequestMapping(value = "/addCart")
    public String addCart(Integer count, Integer goodId, HttpSession session, HttpServletResponse response) throws IOException {

        User user = (User) session.getAttribute("LOGIN_USER");
        if(user==null){
            //没有登录，不能添加到购物车，也不会跳转到购物车列表
            return "login";
        }
        Cart cart=new Cart();
        cart.setGid(goodId);
        cart.setNum(count);
        cart.setUid(user.getId());
        cartService.addCart(cart);
        return "index";
    }
    /** 页面加载时首页显示购物车的数量*/
    @RequestMapping(value = "/selectCartCounts")
    @ResponseBody
    public int selectCartCounts(HttpSession session){
        int count=0;
        //从session中拿到用户名
        User user = (User) session.getAttribute("LOGIN_USER");
        //根据用户id查询购物车的数量
     if(user!=null){
        count=cartService.getCartCounts(user.getId());
     }
      return count;
    }
    /** 页面点击购物车数量的时候,跳转到购物车列表页面，并进行展示*/
    @RequestMapping(value = "/getCartList")
    public String getCartList(ModelMap map,HttpSession session){

        //通过用户id查询它的购物车的集合
        //从session中拿到用户名
        User user = (User) session.getAttribute("LOGIN_USER");

        if(user!=null){
            //要进行去重，相同的商品要进行合并，并重新计算数量和小计
           List<Cart> cartList= cartService.getCartListByUId(user.getId());
            Set<Integer> set=new HashSet<>();
            Map<Integer,Cart> cartMap=new HashMap<>();
            for (Cart cart : cartList) {
                if (set.add(cart.getGid())) {//为ture,说明商品id不重复
                    //存到map中，key(goodsId)----value(Cart)
                    cartMap.put(cart.getGid(), cart);
                } else {
                    //商品id有重复，相同的商品id,这两辆购物车合并成一辆，数量和价格重新计算
                    //拿到存在map中无重复的这个商品的数量
                    Integer oldNum = cartMap.get(cart.getGid()).getNum();
                    //拿到现在重复的商品id的数量
                    Integer num = cart.getNum();
                    //合并数量
                    cart.setNum(num + oldNum);
                    //重新计算小计
                    cart.setSubTotal(cart.getSubTotal() + cartMap.get(cart.getGid()).getSubTotal());
                    //覆盖原来的购物车，变成一辆新的购物车，没有重复
                    cartMap.put(cart.getGid(), cart);

                }
            }
                //把去重后存在cartMap中的集合遍历存在list中，方便前端遍历
              if(!cartMap.isEmpty()){//不为空
                  //先清空原来的cartList
                  cartList.clear();
                  //遍历cartMap
                  Set<Map.Entry<Integer, Cart>> entries = cartMap.entrySet();
                  for (Map.Entry<Integer, Cart> entry : entries) {
                      //value是一辆购物车
                      //把map中的数据放到集合中value是一辆购物车
                      cartList.add(entry.getValue());
                  }

              }
            //算出所有商品的总价，在购物车列表中，最右下角有一个总价
            //所有购物车的小计加起来就是所有商品的总价了
            //先遍历购物车集合，每辆购物车的商品的小计进行相加
            Double totalPrice=0.0;
            for (Cart cart1 : cartList) {
                totalPrice+=cart1.getSubTotal();
            }
            //商品的件数
            int cartSize = cartList.size();
            //跳转到购物车页面，进行展示(有商品信息和购物车信息)
            session.setAttribute("cartList",cartList);
            session.setAttribute("totalPrice",totalPrice);
            map.put("cartSize",cartSize);
            return "shopCart";

        }
        return "index";
        }
  /** 后端的操作*/


  /** 查询商品的类型*/
  @RequestMapping(value = "/selectGoodsType")
    public String selectGoodsType(ModelMap map,HttpSession session){
      List<GoodsType> goodsTypeList = goodsTypeService.getList();
      session.setAttribute("gtList",goodsTypeList);
      return "back/goodstype/goodsType";
  }
  /** 添加商品小类到相应的大类中*/
   @RequestMapping(value = "/addGoodsType")
    public void addGoodsType(GoodsType goodsType,HttpServletResponse response) throws IOException {
       response.setContentType("text/html;charset=utf-8");
       //接收商品类别的小类的名称和大类的id
       //添加到商品类别表中
       int result=goodsTypeService.addGoodsType(goodsType);
       if(result>0){
           response.getWriter().write("<script language=\"javascript\">alert('添加商品类别成功');location.href='http://localhost:8080/goods/selectGoodsType'</script>");

       }else{
           response.getWriter().write("<script language=\"javascript\">alert('添加商品类别失败');location.href='http://localhost:8080/goods/selectGoodsType'</script>");
       }
   }
   /**点击商品管理，查询商品集合 */
   @RequestMapping(value = "/backGoods")
    public String backGoods(Page page,ModelMap map){
       PageInfo<GoodsInfo> pageInfo = goodsInfoService.getPage(page.getPageNum(), page.getPageSize());
       map.put("url","goods/backGoods");
       map.put("page",pageInfo);
       return "back/goods/goodsList";

   }
   /** 点击商品编辑，进行商品回显*/
   @RequestMapping(value = "/update")
    public String update(Integer goodsId,ModelMap map){
       //根据商品id查询商品对象
       //查询商品类别的集合
       GoodsInfo goodsInfo = goodsInfoService.getById(goodsId);
       List<GoodsType> list = goodsTypeService.getList();
       map.put("goodInfo",goodsInfo);
       map.put("gtList",list);

       return "back/goods/updateGoods";
   }
   /** 根据商品id进行商品修改*/
   @RequestMapping(value = "/modifyGoods")
    public void modifyGoods(GoodsInfo goodsInfo,HttpServletResponse response) throws IOException {
       response.setContentType("text/html;charset=utf-8");
       goodsInfo.setId(goodsInfo.getId());
       int result = goodsInfoService.update(goodsInfo);
       if(result>0){
           response.getWriter().write("<script language=\"\">alert('修改商品成功');location.href='http://localhost:8080/goods/backGoods'</script>");
       }else{
           response.getWriter().write("<script language=\"\">alert('修改商品失败');location.href='http://localhost:8080/goods/backGoods'</script>");
       }

   }


}

