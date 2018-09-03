<%--
  Created by IntelliJ IDEA.
  User: 073105
  Date: 2018/8/16
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录界面</title>
    <style type="text/css">

        .center{
           text-align: center;
            line-height: 150%;
            background-color: beige;
        }

        .right{
            text-align: right;
            margin-right: 10%;
        }


        .end{
            margin-top: 30%;

        }

        .width8{
            width: 80%;
        }

        .width2{
            width: 20%;
        }

        .height10{
            height: 100%;
        }

    </style>
</head>
<body bgcolor="#ffe4c4">

    <div class="right">
        <a href="/springbatis/page/register">注册</a>
    </div>

    <div class="right end ">
            <form  action="${pageContext.request.contextPath}/user/login.do" method="post">
                账号:<input type="text" name="userName" id="phoneNum"><br/>
                密码:<input type="password" name="passWd" id="userName"><br/>
                <input type="submit" value="登录"/>
            </form>
    </div>



</body>
</html>
