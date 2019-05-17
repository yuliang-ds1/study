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

        for(int i =0;i<10;i++){
            new Thread(
            ){
                @Override
                public void run() {
                    DruidDao druidDao = new DruidDao();
                    String sql = "insert into test (name) values(\"keven\")";
                    druidDao.insert(sql);
                }
            }.start();


        }

    }
}
