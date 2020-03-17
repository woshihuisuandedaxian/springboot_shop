package com.qf.dao;

import com.qf.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface IUserMapper extends Mapper<User> {

    User checkLogin(User user);

    User backLogin(User user);
}