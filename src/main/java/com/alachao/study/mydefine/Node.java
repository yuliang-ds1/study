package com.alachao.study.mydefine;

public class Node {

    Node pre;
    Node next;
    int  key;
    String value;

    public Node(){}

    public Node(int key,String value){
        this.key=key;
        this.value=value;


    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Node{" +
                "pre=" + pre +
                ", next=" + next +
                ", key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
