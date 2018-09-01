<%--
  Created by IntelliJ IDEA.
  User: 073105
  Date: 2018/8/16
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>注册界面</title>
    <style type="text/css">
        .colorred{
            color: red;
        }
    </style>
</head>
<body>


    <c:forEach var="error" items="${errors}">
        <span class="colorred">
            <c:out value="${error}"/><br/>
        </span>
    </c:forEach>
    <div>

        <form  action="/springbatis/user/register" method="post" enctype="multipart/form-data"><br/>
            手机号:<input type="text" name="phoneNum"><br/>
            用户名:<input type="text" name="userName"><br/>
            密码:<input type="password" name="passWd"><br/>
            确认密码:<input type="password" name="passWd2"><br/>
            头像:<input type="file"  name="userimgpath"><br/>
            <input type="submit" value="注册">
        </form>

    </div>

</body>
</html>
