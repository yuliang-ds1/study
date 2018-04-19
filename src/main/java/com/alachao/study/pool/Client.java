package com.alachao.study.pool;

/**
 * @Author yuliang-ds1
 * @Date 11:27  2018/4/18.
 * @Desciption
 */
public class Client {
    /**
     * @param args
     */
    public static void main(String[] args) {
        DruidDao druidDao = new DruidDao();
        String sql = "insert into test (name) values(\"keven\")";
        druidDao.insert(sql);
    }
}
