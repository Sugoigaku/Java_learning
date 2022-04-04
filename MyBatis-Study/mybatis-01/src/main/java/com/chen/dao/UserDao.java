package com.chen.dao;

import com.chen.pojo.User;

import java.util.List;

public interface UserDao {
    //获取用户列表
    List<User> getUserList();

    //根据id查找用户
    User getUserById(int id);

    //添加用户
    int addUser(User user);

    //更新用户
    void updateUser(User user);

    //删除用户
    void deleteUserById(int id);
}
