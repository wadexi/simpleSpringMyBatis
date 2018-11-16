<%--
  Created by IntelliJ IDEA.
  User: 073105
  Date: 2018/8/31
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>活动展示</title>
    <style type="text/css">

        .divbg{
            background-size: contain;
        }

    </style>
</head>
<body>

    <c:out value="活动展示"/>

    <c:forEach items='<%= request.getAttribute("activities")%>' var="item">

        <div style="background-image: ${item.imgPath};background-size: contain" >
            <h3>${item.title}</h3>
            时间:${item.date}
            地点:${item.location}
            简介:${item.intro}
        </div>
    </c:forEach>
</body>
</html>
