package com.chen.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest2 {

    @Test
    public void testSelectById(){
        User user = new User();
        user.setId(2L);
        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("玄德");
        user.setAge(12);
        user.setUserName("liubei");
        user.setPassword("1234445");
        user.setMail("liubei@qwe");

        boolean insert = user.insert();
        System.out.println(insert);
    }


    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(8L);
        user.setName("张飞");
        user.setAge(14);
        user.setUserName("zhangfei");
        user.setPassword("1234445");
        user.setMail("张飞@qwe");

        boolean update = user.updateById();
        System.out.println(update);
    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setId(7L);

        boolean delete = user.deleteById();
        System.out.println(delete);
    }

    @Test
    public void testSelectList(){
        User user = new User();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 10);

//        SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age >= ?
        List<User> userList = user.selectList(wrapper);
        for (User user1 : userList) {
            System.out.println(user1);
        }

    }
}
