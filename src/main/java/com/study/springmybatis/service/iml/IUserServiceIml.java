package com.study.springmybatis.service.iml;

import com.study.springmybatis.dao.UserDao;
import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
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
}
