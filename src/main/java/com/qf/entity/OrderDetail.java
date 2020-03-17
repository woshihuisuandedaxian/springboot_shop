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
@Table(name = "t_order_detail1")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer oOrderid;

    private Integer goodsid;

    private String goodsname;

    private Double goodsprice;

    private String goodsDescription;

    private Integer goodsnum;

    private String goodspic;

    private Double goodsTotalPrice;


}