package com.alachao.study.mybatis;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author yuliang-ds1
 * @Date 13:32  2018/5/2.
 * @Desciption
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface UserService {
    public User getUser(Integer id);
}
