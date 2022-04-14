package com.chen.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user")  //在application.properties进行了全局配置
public class User extends Model<User> {//继承Model类之后，可以用ActiveRecord
//@TableId = 指定id的各种格式（这里可能有点问题）
//    @TableId(type= IdType.NONE)
    private Long id;
    private String userName;
//    这个数据不会从数据库回填到java实例
    @TableField(select = false)
    private String password;
    private String name;
    private Integer age;

//    可以指定与数据库field之间的对应，变量名可不用满足默认规则
    @TableField("email")
    private String mail;

// 可以添加表中不存在的变量，数据库插入时不会报错
    @TableField(exist = false)
    private String address;
}
