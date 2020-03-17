package com.qf.service;

import com.qf.common.service.IBaseService;
import com.qf.entity.User;

/**
 * @author ZouXianTao
 * @Date2019/12/25
 */
public interface IUserService extends IBaseService<User> {
    User checkLogin(User user);

    User backLogin(User user);
}
