<%--
  Created by IntelliJ IDEA.
  User: 073105
  Date: 2018/8/16
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建活动</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/activity/create" method="post">
        标题:<input name="title" type="title"/><br/>
        活动宣传图：<input name="imgPath" type="file"/><br/>
        地点:<input type="text" name="location" ><br/>
        时间:<input type="datetime-local" name="date" ><br/>
        简介:<textarea name="intro"></textarea><br/>
        <input type="submit" value="创建">
    </form>


</body>
</html>
