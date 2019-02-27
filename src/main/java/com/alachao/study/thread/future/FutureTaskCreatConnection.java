package com.alachao.study.thread.future;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author yuliang-ds1
 * @Date 2019/2/27 16:11
 * @Description
 */
public class FutureTaskCreatConnection {

    public ConcurrentHashMap<String,FutureTask<Connection>> connectionPool=new ConcurrentHashMap();


    public  Connection  getConnection(String key) throws ExecutionException, InterruptedException {

       FutureTask<Connection>  connectionFutureTask= connectionPool.get(key);

       if(null!=connectionFutureTask){
           return connectionFutureTask.get();
       }else{
           Callable call=new Callable(){

               public Object call() throws Exception {
                   Connection connection=createConnection();
                   return connection;
               }
           };

           FutureTask<Connection>  futureTask=new FutureTask<Connection>(call);
           connectionFutureTask=connectionPool.putIfAbsent(key,futureTask);
           if(null==connectionFutureTask){
               connectionFutureTask=futureTask;
               connectionFutureTask.run();
           }
           return connectionFutureTask.get();
       }
    }

    //创建Connection
    private Connection createConnection() {
        return null;
    }

}



