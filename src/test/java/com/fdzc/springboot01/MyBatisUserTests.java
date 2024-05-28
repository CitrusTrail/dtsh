package com.fdzc.springboot01;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.mapper.DeptMapper;
import com.fdzc.springboot01.mapper.UserMapper;
import com.fdzc.springboot01.vo.DeptVo;
import com.fdzc.springboot01.vo.UserVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisUserTests {

    private static SqlSession getSqlSession(){
        //读取MyBaits的核心配置文件
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建SqlSessionFactory对象(通过创建SqlSessionFactoryBuilder来进行获取)
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //创建sqlSession对象（MyBatis的操作数据库的会话对象，通过此对象可以获取SQL语句，并执行）
        //注意：openSession对象默认是不会进行自动的事务提交的,所以我们如果想做DML操作并且自动事务提交，需要加上true参数，默认为false
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession;
    }

    @Test
    public void selectUserByIdTest(){
        SqlSession sqlSession = MyBatisUserTests.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void selectAllUserTest(){
        SqlSession sqlSession = MyBatisUserTests.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);
    }

    @Test
    public void selectUserDeptTest(){
        SqlSession sqlSession = MyBatisUserTests.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<UserVo> userVos = userMapper.selectUserDept();
        userVos.forEach(System.out::println);
    }

    @Test
    public void insertUserTest(){
        SqlSession sqlSession = MyBatisUserTests.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null,"class2ZZZ","753");
        Integer num = userMapper.insert(user);
        System.out.println(num);
    }

    @Test
    public void updateUserTest(){
        SqlSession sqlSession = MyBatisUserTests.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(13,"honghaier1111","753");
        Integer num = userMapper.update(user);
        System.out.println(num);
    }

    @Test
    public void deleteUserTest(){
        SqlSession sqlSession = MyBatisUserTests.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer num = userMapper.delete(13);
        System.out.println(num);
    }


}
