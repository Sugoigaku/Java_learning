package com.chen.mp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.chen.mp.mapper.UserMapper;
import com.chen.mp.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(10);
        user.setMail("wwwww");
        user.setUserName("binbin");
        user.setName("彬彬");
        user.setPassword("1234");
        user.setAddress("aksldfkasdlkfj");

//      执行mp自带的insert，返回的是受影响的行数
        int result = userMapper.insert(user);
        System.out.println("result => " + result);
//      mp会自动回填id到Java实例
        Long id = user.getId();
        System.out.println("id => " + id);
    }

    @Test
    public void testSelectOne(){
        System.out.println(userMapper.selectById(3));
    }

//    int updateById(@Param(Constants.ENTITY) T entity);
//    实例中设置id，方法通过该id进行更新操作
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1L);
        user.setAge(19);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    /**
     *  根据 whereEntity 条件，更新记录
     *  entity 实体对象 (set 条件值,可以为 null)
     *  updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句） */
//    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
    @Test
    public void testUpdate(){
//        更新条件1 用QueryWrapper包装类
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//      equal方法  第一个参数是表的字段名
        userQueryWrapper.eq("id", 2L);

        //        更新条件2 用UpdateWrapper包装类
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//      equal方法  第一个参数是表的字段名
        updateWrapper.eq("id", 3L);

        User user = new User();
        user.setAge(1032);
        user.setPassword("899999");

        int result = userMapper.update(user, updateWrapper);
        System.out.println(result);
    }
}
