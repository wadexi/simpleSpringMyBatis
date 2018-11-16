package com.study.springmybatis.controller;

import com.study.springmybatis.bean.ActivityType;
import com.study.springmybatis.dao.UserDao;
import com.study.springmybatis.entity.Activity;
import com.study.springmybatis.entity.User;
import com.study.springmybatis.service.iml.IUserServiceIml;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private IUserServiceIml userServiceIml;


    /**
     *跳转到注册界面
     */
    @RequestMapping(path = "/page/register")
    public String showRegister(){
        return "register";
    }


    /**
     * 跳转到创建活动界面
     * */
    @RequestMapping(path = "/page/createactivity")
    public String showCreateActiviy(Model model){


        return "createactivity";
    }


    /**
     * 创建活动跳转到活动创建成功界面
     * @param file
     * @param activity
     * @param model
     * @param request
     * @return
     */
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
     * @usein 创建活动
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

    /**
     * 注册账号跳转主界面
     * */
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
//            path= request.getSession().getServletContext().getRealPath("/source/photos/" +filename);
//            ip+port+contextpath+/source/photos/+filename

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

    /**
     * 通过cookie登录
     * */
    @RequestMapping(path = "/user/logincookie",method = {RequestMethod.POST})
    public String doLoginCookie(Model model,HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "forward:/user/login";
        }

        HttpSession session = request.getSession();
        String seesionId = session.getId();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("JSESSIONID")){
                if(!cookie.getValue().equals(seesionId)){
                    return "forward:/user/login";
                }
            }
        }

        for (Cookie cookie2:cookies){
            if(cookie2.getName().equals("username") && cookie2.getValue() != null){
                try{
                    String cookieUserName = cookie2.getValue();
                    String realPassWd = userServiceIml.getUserByName(cookieUserName).getPassWd();
                    if(session.getAttribute("password").equals(realPassWd)){
                        return "home";
                    }else{
                        return "forward:/index";
                    }
                }catch (Exception ex){
                    return "forward:/index";
                }
            }
        }

        return "forward:/index";
    }

    /**
     * 通过账户名密码登录
     * */
    @RequestMapping(path = "/user/login",method = {RequestMethod.POST})
    public String doLogi(User user, Model model, HttpServletRequest request, HttpServletResponse response){
        System.out.println(user.toString());
        User dbUser = userServiceIml.getUserByName(user.getUserName());
        if(dbUser != null){
            final String dbPassWd = dbUser.getPassWd();
            final String passWd = user.getPassWd();
            if(dbPassWd.equals(passWd)){

                /*关联这个请求的session*/
                HttpSession session = request.getSession();
                session.setAttribute("username",user.getUserName());
                session.setAttribute("password",user.getPassWd());

                Cookie usernameCookie = new Cookie("username",user.getUserName());
                usernameCookie.setMaxAge(500);
                usernameCookie.setPath("/");
                response.addCookie(usernameCookie);

                /*客户端发送给服务器的cookies*/
                Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for (Cookie cookie:cookies){
                        if(cookie.getName().equals("JSESSIONID")){
                            cookie.setValue(session.getId());
                            cookie.setPath("/");
                            cookie.setMaxAge(500);
                            response.addCookie(cookie);
                        }
                    }
                }



                request.getServletContext().setAttribute("user",dbUser);

//                model.addAttribute("user",dbUser);
                List<Activity> activities = userServiceIml.getAllActivities();
                System.out.println("活动数据源：" + activities.toString());
                model.addAttribute("activities",activities);
                System.out.println(activities.toString());
                return "home";
            }
        }
        return "index";
    }


    /**
     * 登出账号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Cookie usernameCookie = new Cookie("username","");
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        return "redirect:index";
    }

    /**
     * 跳转到登录界面
     * */
    @RequestMapping({"/"})
    public String showLoginPage(){
        System.out.println("index page");
        return "index";
    }

    /**
     * 跳转到主界面
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/homepage")
    public String showHomePage(HttpSession session,HttpServletRequest request){
        System.out.println("home page");
//        session.getAttribute()
        return "home";
    }

    @ResponseBody
    @RequestMapping("/testjqueryajax")
    public Activity testjqueryajax(HttpSession session,HttpServletRequest request){
        System.out.println("home page");
//        session.getAttribute()
        return new Activity();
    }

    @ResponseBody
    @RequestMapping(path = "/user/info")
    public User getUserInfo(HttpServletRequest request,HttpSession session){

//        Cookie[] cookies = request.getCookies();
//        if(cookies == null){
//            return null;
//        }
//
//        HttpSession session = request.getSession();
//        String sessionId = session.getId();
//
//
//        for (Cookie cookie:cookies){
//            if(cookie.getName().equals("JSESSIONID")){
//                if(cookie.getValue().equals(sessionId)){
                    String username = (String) session.getAttribute("username");
                    User user = userServiceIml.getUserByName(username);
                    System.out.println("获取到user信息：" + user.toString());
                    return user;
//                }
//            }
//        }

//        return null;
    }




    @ResponseBody
    @RequestMapping(path = "/activities/all",method = RequestMethod.GET)
    public List<Activity> getActivities(HttpServletRequest request){
        List<Activity> activities =  userServiceIml.getAllActivities();
        System.out.println("活动信息："  + activities.toString());
        return activities;
    }


}
