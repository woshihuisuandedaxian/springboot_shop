package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** 购物车id*/
    private Integer id;
  /** 商品详情id*/
    private Integer gid;
   /** 商品数量*/
    private Integer num;
  /** 用户id*/
    private Integer uid;
  /** 商品小计*/
    private Double subTotal;
  /** 创建时间*/
    private Date createTime;
    /** 数据库表中没有，只是为了方便购物车实体类中调用要商品的信息*/
   @Transient
    private GoodsInfo goodsInfo;

   
}