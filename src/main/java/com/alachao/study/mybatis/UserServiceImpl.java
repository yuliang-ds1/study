package com.alachao.study.mybatis;

import org.springframework.stereotype.Service;

/**
 * @Author yuliang-ds1
 * @Date 13:33  2018/5/2.
 * @Desciption
 */
public class UserServiceImpl implements  UserService{

    private  UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
