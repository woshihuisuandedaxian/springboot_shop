package com.qf.dao;

import com.qf.entity.GoodsType;
import tk.mybatis.mapper.common.Mapper;

public interface IGoodsTypeMapper extends Mapper<GoodsType> {

    int addGoodsType(GoodsType goodsType);
}