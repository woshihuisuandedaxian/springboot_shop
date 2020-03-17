package com.qf.service;

import com.qf.common.service.IBaseService;
import com.qf.entity.GoodsType;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
public interface IGoodsTypeService extends IBaseService<GoodsType> {
    int addGoodsType(GoodsType goodsType);
}
