package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_order1")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String oSendtype;

    private String oPaytype;

    private Double oPaycount;

    private Date oOrderdate;

    private Integer oCheckstate;

    private Date oCheckdate;

    private String oCheckperson;

    private Integer userid;

    private String oShperson;

    private String oShphone;

    private String oShaddress;
    /** 一张订单有多张订单详情*/
   @Transient
    private List<OrderDetail> orderDetailList;


}