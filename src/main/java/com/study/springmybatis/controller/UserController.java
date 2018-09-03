package com.study.springmybatis.controller;

import com.study.springmybatis.bean.ActivityType;
import com.study.springmybatis.entity.Activity;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public String showCreateActiviy(Model model){


        return "createactivity";
    }


    @RequestMapping(path = "/activity/create")
    public String createActiviy(@RequestParam("imgpath") CommonsMultipartFile file,Activity activity,Model model,HttpServletRequest request){
        System.out.println(activity.toString());
        List<String> errors = new ArrayList<>();
        StringBuffer relPath = new StringBuffer("/source/photos/");
        StringBuffer absolutePath = new StringBuffer(request.getSession().getServletContext().getRealPath(relPath.toString()));
        String errorMsg = saveFile(file,relPath,absolutePath);

        if(StringUtils.isNotBlank(errorMsg)){
            errors.add(errorMsg);
        }

        if(!errors.isEmpty()){
            return "createactivity";
        }
        activity.setImgPath(relPath.toString());
        activity.setAcitityType(ActivityType.BOOK.name());
        userServiceIml.createActivity(activity);
        return "activitycreatesuccesed";
    }

    /**
     * 保存文件
     * @param file
     */
    private String saveFile(CommonsMultipartFile file,StringBuffer relPath,StringBuffer absolutePath) {
        String errorMsg = "";
        if(!file.isEmpty()){
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String filename = System.currentTimeMillis() + type;
            relPath.append(filename);
            absolutePath.append(filename);


            File destFile = new File(absolutePath.toString());
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            errorMsg = "文件不可为空!";
        }

        return errorMsg;
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
        String relPath = "";
        if(!file.isEmpty()){
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String filename = System.currentTimeMillis() + type;
            relPath = "/source/photos/" + filename;
            path= request.getSession().getServletContext().getRealPath("/source/photos/" +filename);


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
        user.setUserImgPath(relPath);
        userServiceIml.addUser(user);
        model.addAttribute("user",user);

        return "home";
    }

    @RequestMapping(path = "/user/login")
    public String doLogi(User user,Model model,HttpServletRequest request){
        System.out.println(user.toString());
        User dbUser = userServiceIml.getUserByName(user.getUserName());
        if(dbUser != null){
            final String dbPassWd = dbUser.getPassWd();
            final String passWd = user.getPassWd();
            if(dbPassWd.equals(passWd)){
                request.getServletContext().setAttribute("user",dbUser);
//                model.addAttribute("user",dbUser);
                List<Activity> activities = userServiceIml.getAllActivities();
                model.addAttribute("activities",activities);
                System.out.println(activities.toString());
                return "home";
            }
        }
        return "index";
    }

    @RequestMapping({"/"})
    public String showLoginPage(){
        System.out.println("index page");
        return "index";
    }

    @RequestMapping("/homepage")
    public String showHomePage(HttpSession session,HttpServletRequest request){
        System.out.println("home page");
//        session.getAttribute()
        return "home";
    }


}
