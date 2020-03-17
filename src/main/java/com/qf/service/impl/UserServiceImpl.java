package com.qf.service.impl;

import com.qf.common.service.impl.BaseServiceImpl;
import com.qf.dao.IUserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ZouXianTao
 * @Date2019/12/25
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    private IUserMapper userMapper;
    @Override
    protected Mapper<User> getMapper() {
        return userMapper;
    }
   /** 验证用户登录*/
    @Override
    public User checkLogin(User user) {
       User user1= userMapper.checkLogin(user);
       if(user1!=null){
           return user1;
       }
        return null;
    }
   /** 后端登录*/
    @Override
    public User backLogin(User user) {
        return userMapper.backLogin(user);

    }
}
