package com.study.springmybatis.service.iml;

import com.study.springmybatis.dao.UserDao;
import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
}
