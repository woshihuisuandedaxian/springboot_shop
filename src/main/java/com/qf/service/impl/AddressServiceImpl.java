package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.IAddressMapper;
import com.qf.entity.Address;
import com.qf.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ZouXianTao
 * @Date2019/12/26
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements IAddressService{
    @Autowired
    private IAddressMapper addressMapper;
    @Override
    protected Mapper<Address> getMapper() {
        return addressMapper;
    }
  /** 根据用户id查询地址集合*/
    @Override
    public List<Address> getAddressListByUid(Integer uid) {
        return addressMapper.getAddressListByUid(uid);
    }
}
