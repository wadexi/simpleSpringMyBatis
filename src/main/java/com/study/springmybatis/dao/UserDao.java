package com.study.springmybatis.dao;

import com.study.springmybatis.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component()
public interface UserDao {

    User getUser(String userId);
}
