package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.IGoodsInfoMapper;
import com.qf.entity.GoodsInfo;
import com.qf.service.IGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
@Service
public class GoodsInfoServiceImpl extends BaseServiceImpl<GoodsInfo> implements IGoodsInfoService {
    @Autowired
    private IGoodsInfoMapper goodsInfoMapper;
    @Override
    protected Mapper<GoodsInfo> getMapper() {
        return goodsInfoMapper;
    }
}
