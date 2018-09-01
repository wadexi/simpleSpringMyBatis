package com.study.springmybatis.service.iml;

import com.study.springmybatis.dao.UserDao;
import com.study.springmybatis.entity.Activity;
import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceIml implements IUserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUser(id);
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public User getUserByPhoneNum(String phoneNum) {
        return userDao.getUserByPhoneNum(phoneNum);
    }

    @Override
    public List<Activity> getAllActivities() {
        return userDao.getAllActivities();
    }

    @Override
    public void createActivity(Activity activity) {
        userDao.createActivity(activity);
    }
}
