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

import java.io.Serializable;
import java.util.*;

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

    /*** 根据 ID 删除 ** @param id 主键ID */
//    int deleteById(Serializable id);
    @Test
    public void testDeleteById(){

        int result = userMapper.deleteById(4L);
        System.out.println(result);
    }

    /*** 根据 columnMap 条件，删除记录 ** @param columnMap 表字段 map 对象 */
//    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
    @Test
    public void testDeleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("age", 24);
        map.put("name", "孙七");

//        Preparing: DELETE FROM tb_user WHERE name = ? AND age = ?
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    /**
     *  根据 entity 条件，删除记录
     *  wrapper 实体对象封装操作类（可以为 null） */
//    int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
    @Test
    public void testDelete(){

        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 200);

//        DELETE FROM tb_user WHERE age > ?
        int result = userMapper.delete(wrapper);
        System.out.println(result);
    }

    /**
     *  删除（根据ID 批量删除）
     *  idList 主键ID列表(不能为 null 以及 empty) */
//    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
    @Test
    public void testDeleteBatchIds(){

//        DELETE FROM tb_user WHERE id IN ( ? , ? )
        int result = userMapper.deleteBatchIds(Arrays.asList(9L,10L));
        System.out.println(result);
    }
}
