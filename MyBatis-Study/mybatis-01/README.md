#MyBatis入门
## 1. 创建mybatis项目的步骤
1. 在resources路径下创建mybatis-config.xml核心配置文件，作用是连接数据库，***注册Mapper.xml文件***
2. 编写（封装）一个工具类，便于获得一个SqlSessionFactory的一个实例（用于执行sql语句）
3. 编写一个实体类，pojo
4. 编写Dao（Mapper）接口
5. 编写Mapper.xml文件
6. 测试

### 1.1 可能遇到的问题
1. 配置文件没有注册 -> mybatis-config.xml  <mapper>
2. 绑定接口错误 -> <mapper namespace="com.chen.dao.UserMapper">
3. 方法名不对 -> Mapper.xml文件 select id
4. 返回类型不对 -> 要写全称，不能只写类名
5. maven资源导出问题 -> pom.xml(不行父子pom.xml都改)
    ```<build>
           <resources>
               <resource>
                   <directory>src/main/resources</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.properties</include>
                       <include>**/*.xml</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
           </resources>
       </build>```
## 2. CRUD实现
1. 核心配置文件，<mapper>标签中的各种参数
    * namespace：namespace中的报名要和Dao/Mapper接口的包名一致！
    * id：就是对应的namespace中的方法名
    * resultType：SQL语句执行的返回值
    * parameterType：参数类型
2. 实现步骤
    1. 编写接口
    2. 编写对应的mapper中的sql语句
    3. 测试
3. 注意点！
    * 增删改需要提交事务。
> 参考    
> [mybatis文档](https://mybatis.org/mybatis-3/zh/index.html)    
> [狂神说mybatis](https://www.bilibili.com/video/BV1NE411Q7Nx?p=2)