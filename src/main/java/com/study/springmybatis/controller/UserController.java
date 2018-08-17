package com.study.springmybatis.controller;

import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.iml.IUserServiceIml;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private IUserServiceIml userServiceIml;


    @RequestMapping(path = "/page/register")
    public String showRegister(){
        return "register";
    }

    @RequestMapping(path = "/page/createactivity")
    public String showCreateActiviy(){
        return "createactivity";
    }

    @RequestMapping(path = "/user/register")
    public String doRegister(@RequestParam("userimgpath") CommonsMultipartFile file,User user,Model model, HttpServletRequest request){
        System.out.println(user.toString());
        List<String> errors = new ArrayList<>();
        String phoneNum = user.getPhoneNum();
        String userName = user.getUserName();
        String passWd = user.getPassWd();

        if(StringUtils.isEmpty(phoneNum)){
            errors.add("电话号码不可为空!");
        }else {
            User tempUser = userServiceIml.getUserByPhoneNum(user.getPhoneNum());
            if(tempUser != null){
                errors.add("该电话号码已经被注册!");
            }
        }

        if(StringUtils.isEmpty(userName)){
            errors.add("用户名不可为空!");
        }else {
            User tempUser = userServiceIml.getUserByName(user.getUserName());
            if(tempUser != null){
                errors.add("该用户名已经被注册!");
            }
        }

        if(StringUtils.isEmpty(passWd)){
            errors.add("密码不可为空!");
        }
        String path ="";
        if(!file.isEmpty()){
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String filenam = System.currentTimeMillis() + type;
            path= request.getSession().getServletContext().getContextPath();
            path= request.getSession().getServletContext().getRealPath("/upload/" +filenam);

            File destFile = new File(path);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            errors.add("头像不可为空!");
        }

        if(!errors.isEmpty()){
            model.addAttribute("errors",errors);
            return "register";
        }
        user.setUserImgPath(path);
        userServiceIml.addUser(user);
        model.addAttribute("user",user);

        return "home";
    }

    @RequestMapping(path = "/user/login")
    public String doLogi(User user,Model model){
        System.out.println(user.toString());
        User dbUser = userServiceIml.getUserByName(user.getUserName());
        if(dbUser != null){
            final String dbPassWd = dbUser.getPassWd();
            final String passWd = user.getPassWd();
            if(dbPassWd.equals(passWd)){
                model.addAttribute("user",user);
                return "home";
            }
        }
        return "index";
    }

    @RequestMapping({"/","/homepage"})
    public String showLoginPage(){
        System.out.println("index page");
        return "index";
    }


}
