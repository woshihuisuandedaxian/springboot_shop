package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_goods_info")
public class GoodsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String goodsName;

    private String goodsDescription;

    private String goodsPic;

    private Double goodsPrice;

    private Integer goodsStock;

    private Double goodsPriceOff;

    private Double goodsDiscount;

    private Integer goodsFatherid;

    private Integer goodsParentid;

    private String isdelete;


}