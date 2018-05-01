package com.alachao.study.mybatis;

public interface UserMapper {
    public void insertUser(User user);
    public User getUser(Integer id);
}
