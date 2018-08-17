package com.study.springmybatis.service;

import com.study.springmybatis.entity.User;

public interface IUserService {

    User getUserById(String id);


    User getUserByName(String userName);

    void addUser(User user);

    User getUserByPhoneNum(String phoneNum);
}
