package com.test;

import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.iml.IUserServiceIml;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    private IUserServiceIml iUserServiceIml;

    @org.junit.Test
    public void test(){
        User user = iUserServiceIml.getUserById("1");
        System.out.println(user.toString());
    }
}
