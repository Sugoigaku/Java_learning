package com.chen.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mp.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
