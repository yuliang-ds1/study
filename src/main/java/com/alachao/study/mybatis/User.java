package com.alachao.study.mybatis;

public class User {


    private Integer id;
    private String name;
    private Integer age;

    public User(){
        super();
    }

    public User(String name,Integer age){
        super();
        this.name=name;
        this.age=age;

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
