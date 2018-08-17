package com.study.springmybatis.dao;

import com.study.springmybatis.entity.User;
import org.springframework.stereotype.Component;


@Component
public interface UserDao {

    /**
     * 根据id查询user
     * */
    User getUser(String userId);

    /**
     * 根据userNam查询user
     * */
    User getUserByName(String userNam);


    /**
     * 插入用户数据
     * @return
     */
    void insertUser(User user);


    /**
     * 根据phoneNum查询user
     * */
    User getUserByPhoneNum(String phoneNum);
}
