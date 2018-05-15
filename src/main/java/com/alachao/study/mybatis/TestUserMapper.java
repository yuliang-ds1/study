package com.alachao.study.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserMapper {

    public TestUserMapper(){
        System.out.print("init 构造");
    }

    private static SqlSessionFactory sqlSessionFactory;
    static{
        sqlSessionFactory=MybatisUtil.getSqlSessionFactory();
    }

    @Test
    public void testAdd(){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("yu", 27);
            userMapper.insertUser(user);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }



    @Test
    public void getUser(){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User result=userMapper.getUser(1);
            System.out.println("结果：user ："+result);
        }finally {
            sqlSession.close();
        }
    }





}
