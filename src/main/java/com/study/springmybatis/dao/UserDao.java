package com.study.springmybatis.dao;

import com.study.springmybatis.entity.Activity;
import com.study.springmybatis.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;


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


    /**
     * 获取创建的所有活动
     * @return
     */
    List<Activity> getAllActivities();

    /**
     * 创建活动
     * @return
     */
    void createActivity(Activity activity);
}
