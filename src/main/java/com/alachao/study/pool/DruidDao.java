package com.alachao.study.pool;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author yuliang-ds1
 * @Date 11:27  2018/4/18.
 * @Desciption
 */
public class DruidDao {
    public void insert(String sql){
        DBPoolConnection dbp = DBPoolConnection.getInstance();    //获取数据连接池单例
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dbp.getConnection();    //从数据库连接池中获取数据库连接
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps){
                    ps.close();
                }
                if (null != conn){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
