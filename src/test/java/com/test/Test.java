package com.test;

import com.study.springmybatis.dao.UserDao;
import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.iml.IUserServiceIml;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    ApplicationContext applicationContext;

    @org.junit.Test
    public void test(){
//        IUserServiceIml iUserServiceIml = applicationContext.getBean(IUserServiceIml.class);
//        User user = new User();
//        user.setPhoneNum("13162708688");
//        user.setUserName("alan");
//        user.setUserImgPath("http://www.baidu.com/logo.png");
//        user.setPassWd("123123123");
//        iUserServiceIml.addUser(user);
//        User user2 = iUserServiceIml.getUserById("2");
//        System.out.println(user2.toString());

//        StringBuffer relPath = new StringBuffer("/source/photos/");
//        String absolutePath = "1111";
//        saveFile(relPath,absolutePath);
//        System.out.println(relPath);
//        System.out.println(absolutePath);


//        User student = new User();
//        UserDao studentDao = org.springframework.mock.web.MockServletContext.mock(UserDao.class);
//        studentDao.add(student);
//        verify(studentDao).add(student);

    }

    public void saveFile(StringBuffer relPath,String absolutePath){
        relPath.append("9999999");
        absolutePath = absolutePath + "9999999";
    }
}
