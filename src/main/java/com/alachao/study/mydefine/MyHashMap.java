package com.alachao.study.mydefine;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.aspectj.weaver.patterns.HasMemberTypePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyHashMap {
    int capacity;

    List<Object> list;

    public MyHashMap(int capacity){
        this.capacity=capacity;
        list=new ArrayList<Object>(capacity);
        for (int i=0;i<capacity;i++){
            list.add(null);
        }
    }

    public  void set( int key,String value){

        int hashCode=new Integer(key).hashCode();
        System.out.println("key:"+key+"   hashCode"+hashCode);
        hashCode=hashCode%capacity;

        if(list.size()==0||null==list.get(hashCode)){
            Node node=new Node(key,value);
            list.set(hashCode,node);
        }else{
            Object o = list.get(hashCode);
            Node  node=(Node)o;
            int key1 = node.getKey();
            if(key1==key){
                node.setValue(value);
            }
            Boolean  flag=true;
            while(node.next!=null){
                Node n=node.next;
                if(n.getKey()==key){
                    n.setValue(value);
                    flag=false;
                }
                node=n;

            }
            if(flag){
                Node  a=new Node(key,value);
                a.next=node;
                list.set(hashCode,a);

            }
        }

    }



    public String get(int key) {
        int hashCode = new Integer(key).hashCode();
        System.out.println("key:" + key + "   hashCode" + hashCode);
        hashCode = hashCode % capacity;
        Object o = list.get(hashCode);
        if (null == o) {
            return null;
        } else {
            Node node = (Node) o;
            int key1 = node.getKey();
            if (key1 == key) {
                return node.getValue();
            }
            Boolean flag = true;
            while (node.next != null) {
                Node n = node.next;
                if (n.getKey() == key) {
                    return n.getValue();
                }
                node=n;
            }
            if (flag) {
                return null;
            }

            return null;
        }

    }



    public  static void  main(String args[]){

      /*  MyHashMap myHashMap=new MyHashMap(5);
        System.out.println("list:"+myHashMap.list.size());

        for (int i=0;i<100;i++){
            myHashMap.set(i,"liang"+i);
        }


        System.out.println("list:"+myHashMap.list.toString());
        for (int i=0;i<100;i++){
            System.out.println(myHashMap.get(i));
        }*/




        HashMap hashMap=new HashMap();
        for (int i=0;i<100;i++){
            hashMap.put("String"+i,i);
            hashMap.get("String");
        }


    }

}
