package com.alachao.study.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class MybatisUtil {

    private final static SqlSessionFactory sqlSessionFactory;
    static{
           String resource="mybatis-config.xml";
           Reader reader=null;
            try {
                reader= Resources.getResourceAsReader(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }

            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
    }
    public  static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }


}
