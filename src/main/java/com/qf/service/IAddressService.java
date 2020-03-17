package com.qf.service;

import com.qf.common.service.IBaseService;
import com.qf.entity.Address;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/26
 */
public interface IAddressService extends IBaseService<Address> {
    List<Address> getAddressListByUid(Integer id);
}
