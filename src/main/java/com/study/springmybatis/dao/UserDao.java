package com.study.springmybatis.dao;

import com.study.springmybatis.entity.User;
import org.springframework.stereotype.Repository;


@Repository
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
