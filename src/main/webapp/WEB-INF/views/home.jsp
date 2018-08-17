<%--
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

    </style>
</head>
<body>
    <%----%>
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

    </div>

    <%--left--%>
    <div class="left">
        <a href="/page/createactivity">创建活动</a>
    </div>


</body>
</html>
