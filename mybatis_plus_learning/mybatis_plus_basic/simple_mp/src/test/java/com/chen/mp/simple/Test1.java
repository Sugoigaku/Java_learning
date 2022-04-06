package com.chen.mp.simple;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.chen.mp.simple.mapper.UserMapper;
import com.chen.mp.simple.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Test1 {

    @Test
//    使用常规MyBatis方法
    public void testMybatis() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAll();

        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
//    使用MyBatis+方法
    public void testMP() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        //创建工厂类用MP提供的MybatisSqlSessionFactoryBuilder
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = mapper.findAll();
//        因为UserMapper类继承了MP提供的BaseMapper接口,
//        并且在pojo类中用@TableName("tb_user")注解mapping了数据库中的表
//        所以可以使用MP内置的方法,直接去执行配置好的sql语句,而不是取UserMapper.xml里去找
//        para:查询条件
//        mp同时进行了优化,数据中的表的属性名和pojo类的属性名不完全一样(eg.表的field=user_name,User类=userName,UserName都可,hhhh不行)也可以mapping上
        List<User> userList = mapper.selectList(null);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}
