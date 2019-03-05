package com.study.springmybatis.interceptor;

import com.study.springmybatis.service.iml.IUserServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizedInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserServiceIml iUserServiceIml;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入了preHandle方法！！！！");
        //先从session拿取用户
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            response.sendRedirect("/login");
            return false;
        }
        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect("/login");
            return false;
        }
        String sessionId = session.getId();

        for(Cookie cookie:cookies){
            if (cookie.getName().equals("JSESSIONID")) {
                if(!cookie.getValue().equals(sessionId)){
                    response.sendRedirect("/login");
                    return false;
                }
            }
        }

        for (Cookie cookie2:cookies){
            if(cookie2.getName().equals("username")&&cookie2.getValue()!=null){
                String cookieUsername = cookie2.getValue();
                try{
                    String realPassword = iUserServiceIml.getUserByName(cookieUsername).getPassWd();
                    String password = (String) session.getAttribute("password");
                    if (password.equals(realPassword)){
//                        response.sendRedirect("/homepage");
                        return true;
                    }else{
                        response.sendRedirect("/login");
                        return false;
                    }
                }catch (NullPointerException e){
                    response.sendRedirect("/login");
                    return false;
                }

            }
        }
        response.sendRedirect("/login");
        return false;
    }

    /*调用完成还没渲染视图的时候调用*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("进入了postHandle方法！！！！");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("进入了afterCompletion方法！！！！");
    }
}
