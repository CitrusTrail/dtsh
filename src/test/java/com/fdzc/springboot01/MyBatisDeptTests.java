package com.fdzc.springboot01;

import com.fdzc.springboot01.entity.Dept;
import com.fdzc.springboot01.mapper.DeptMapper;
import com.fdzc.springboot01.vo.DeptVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisDeptTests {

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
    public void selectDeptByIdTest(){
        SqlSession sqlSession = MyBatisDeptTests.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.selectById(1);
        System.out.println(dept);
    }

    @Test
    public void selectAllDeptTest(){
        SqlSession sqlSession = MyBatisDeptTests.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> depts = deptMapper.selectAll();
        depts.forEach(System.out::println);
    }

    @Test
    public void selectDeptUsersTest(){
        SqlSession sqlSession = MyBatisDeptTests.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        DeptVo deptVo = deptMapper.selectDeptUsers(1);
        System.out.println(deptVo);
    }

    @Test
    public void insertDeptTest(){
        SqlSession sqlSession = MyBatisDeptTests.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept(null,"a部","负责aaaaaaaa","999");
        Integer num = deptMapper.insert(dept);
        System.out.println(num);
    }

    @Test
    public void updateDeptTest(){
        SqlSession sqlSession = MyBatisDeptTests.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept(5,"a部","负责aaaaaaaa_273","111");
        Integer num = deptMapper.update(dept);
        System.out.println(num);
    }

    @Test
    public void deleteDeptTest(){
        SqlSession sqlSession = MyBatisDeptTests.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Integer num = deptMapper.delete(5);
        System.out.println(num);
    }

}
