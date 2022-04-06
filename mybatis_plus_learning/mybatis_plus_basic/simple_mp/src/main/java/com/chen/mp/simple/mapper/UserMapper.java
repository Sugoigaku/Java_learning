package com.chen.mp.simple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mp.simple.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
