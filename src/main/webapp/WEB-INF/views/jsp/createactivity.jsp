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
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/WEB-INF/views/laydate/laydate.js"></script>--%>
    <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>

    <form action="${pageContext.request.contextPath}/activity/create" method="post" enctype="multipart/form-data">
        标题:<input name="title" type="title"/><br/>
        活动宣传图：<input type="file" name="imgpath" /><br/>
        地点:<input type="text" name="location" ><br/>
        时间:<input type="text" name="date" id="datetext" placeholder="请选择时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
        <br/>
        简介:<textarea name="intro"></textarea><br/>
        <input type="submit" value="创建">
    </form>


</body>
</html>
