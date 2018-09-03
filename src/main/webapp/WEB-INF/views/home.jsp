<%@ page import="com.study.springmybatis.entity.User" %>
<%@ page import="com.study.springmybatis.entity.Activity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 073105
  Date: 2018/8/16
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>主页</title>
    <style type="text/css">

        .body{
            margin: 0;
            padding: 0;
        }

        .left{
            width: 20%;
            background-color: bisque;
            float: left;
        }

        .right{
            width: 75%;
            background-color: aliceblue;
            float: right;
        }

        .top{
            width: 100%;
            background-color: aliceblue;
            float: top;
        }

        .circle{
            width: 40px;
            height: 40px;
            border-radius: 40px;
        }

        div{
            text-align: left;
        }

        .in{
            display: inline-block;
            vertical-align: middle;
        }

    </style>
</head>
<body>

    <%!
        String imgPath2 = "";
    %>
    <%
        User user = (User) request.getServletContext().getAttribute("user");
        imgPath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + user.getUserImgPath();
        System.out.println(imgPath2);
        List<Activity> activities = (List<Activity>) request.getAttribute("activities");
        System.out.println(activities.toString());
    %>

    <%----%>
    <div class="top">
        <img class="circle" src="<%=imgPath2%>"  >
        <span class="in">${user.userName}</span>
    </div>

    <div class="right">

        <span class="form-select-button">
            <select name="datalimit">
                <option value="">--时间--</option>
                <option value="day">1天内</option>
                <option value="week">1周内</option>
                <option value="month">1月内</option>
                <option value="year">1年内</option>
            </select>

            <select name="location">
                <option value="">--地点--</option>
                <option value="beijing">北京</option>
                <option value="shanghai">上海</option>
                <option value="guangzhou">广州</option>
                <option value="shenzhen">深圳</option>
            </select>


            <select name="type">
                <option value="">--类型--</option>
                <option value="readbook1">读书会1</option>
                <option value="readbook2">读书会2</option>
                <option value="readbook3">读书会3</option>
                <option value="readbook4">读书会4</option>
            </select>
        </span>
        <br>

        <c:forEach items='<%= request.getAttribute("activities")%>' var="item">

            <div style="background-image: ${item.imgPath};background-size: contain" >
                <img src="${item.imgPath}">
                <h3>${item.title}</h3>
                时间:${item.date}<br>
                地点:${item.location}<br>
                简介:${item.intro}<br>
            </div>
        </c:forEach>

    </div>




    <%--left--%>
    <div class="left">
        <a href="/springbatis/page/createactivity">创建活动</a>
    </div>


</body>
</html>
