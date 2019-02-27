package com.alachao.study.lru;

import java.util.HashMap;

/**
 * @Author yuliang-ds1
 * @Date 2019/2/27 10:40
 * @Description
 */
public class LruCacheLinkedHashMap {

    int capacity;
    HashMap<Integer,Node>  map=new HashMap<Integer,Node>();
    Node head=null;
    Node end=null;

    public LruCacheLinkedHashMap(int capacity){
        this.capacity=capacity;
    }

    public  int  get(int key){
        if(map.containsKey(key)){
            Node node=map.get(key);
            remove(node);
            moveToHead(node);
            printNodes("get");
            return node.value;

        }
        printNodes("get");
        return -1;
    }

    public void  set(int key ,int value){

        if(map.containsKey(key)){
            Node oldNode=map.get(key);
            oldNode.value=value;
            remove(oldNode);
            moveToHead(oldNode);
        }else{
            Node newNode=new  Node(key,value);
            if(map.size()>=capacity){
                map.remove(end.key);
                remove(end);
            }
            map.put(key,newNode);
            moveToHead(newNode);
        }

        printNodes("set");
    }


    public  void remove(Node node){

        //1.前节点指向下个节点
        if(node.pre!=null){
            node.pre.next=node.next;
        }else{
            head=node.next;
        }



        //2.后一个节点执行前一个节点
        if(node.next!=null){
            node.next.pre=node.pre;
        }else{
            end=node.pre;
        }



    }
    public  void moveToHead(Node node){
        //1.修改当前指针指向
        node.next=head;
        node.pre=null;

        //2.修改原头部指向
        if(head!=null)head.pre=node;

        //3.置换
        head=node;

        //4.尾部修正
        if(end==null)end=head;


    }




    public void printNodes(String explain) {
        System.out.print(explain + ":" + head.toString());
        Node node = head.next;
        while (node != null) {
            System.out.print(node.toString());
            node = node.next;
        }
        System.out.println();
    }



    public static void main(String[] args) {
        LruCacheLinkedHashMap lruCache3 = new LruCacheLinkedHashMap(5);
        lruCache3.set(1, 100);
        lruCache3.set(2, 200);
        lruCache3.set(3, 300);
        lruCache3.set(4, 400);
        lruCache3.set(5, 500);
        System.out.println("lruCacheTest.get(1):" + lruCache3.get(1));
        lruCache3.set(6, 600);
        System.out.println("lruCacheTest.get(2):" + lruCache3.get(2));
    }

}


class Node{

    int key;
    int value;

     Node pre;
     Node next;

    public Node(int key,int value){
        this.key=key;
        this.value=value;
    }

    @Override
    public String toString() {
        return "key=" + key + ", value=" + value;

    }


}