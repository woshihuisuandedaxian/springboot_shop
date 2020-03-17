package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.IGoodsTypeMapper;
import com.qf.entity.GoodsType;
import com.qf.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
@Service
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType> implements IGoodsTypeService {
    @Autowired
    private IGoodsTypeMapper goodsTypeMapper;
    @Override
    protected Mapper<GoodsType> getMapper() {
        return goodsTypeMapper;
    }
  /** 添加商品类别*/
    @Override
    public int addGoodsType(GoodsType goodsType) {
        return goodsTypeMapper.addGoodsType(goodsType);
    }
}
