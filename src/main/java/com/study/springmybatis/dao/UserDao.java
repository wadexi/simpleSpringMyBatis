package com.study.springmybatis.dao;

import com.study.springmybatis.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public interface UserDao {

    /**
     * 根据id查询user
     * */
    User getUser(String userId);


    /**
     * 插入用户数据
     * @return
     */
    void insertUser(User user);
}
