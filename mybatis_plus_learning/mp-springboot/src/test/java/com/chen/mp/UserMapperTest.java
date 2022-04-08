package com.chen.mp;


import com.chen.mp.mapper.UserMapper;
import com.chen.mp.pojo.User;
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
}
