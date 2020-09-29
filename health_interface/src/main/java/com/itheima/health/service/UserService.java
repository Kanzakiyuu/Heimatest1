package com.itheima.health.service;

import com.itheima.health.pojo.User;

/**
 * @Authour: chenming
 * @Date： 2020/9/26 19:57
 */
public interface UserService {
    User findByUsername(String username);
}
