package com.study.springmybatis.service;

import com.study.springmybatis.entity.Activity;
import com.study.springmybatis.entity.User;

import java.util.List;

public interface IUserService {

    User getUserById(String id);


    User getUserByName(String userName);

    void addUser(User user);

    User getUserByPhoneNum(String phoneNum);

    List<Activity> getAllActivities();

    void createActivity(Activity activity);
}
