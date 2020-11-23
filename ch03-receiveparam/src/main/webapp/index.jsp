<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>提交参数给controller</p><%--要求参数名和处理器的形参名一样--%>

    <form action="receiveproperty.do" method="post">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        <input type="submit" value="提交参数"/>
    </form><br>

    <p>请求参数名和处理器方法的形参名不一样</p>

    <form action="receiveparam.do" method="post">
        姓名：<input type="text" name="myname"/><br>
        年龄：<input type="text" name="myage"/><br>
        <input type="submit" value="提交参数param"/>
    </form><br>

    <p>使用java对象接收请求参数</p>

    <form action="receiveobj.do" method="post">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        <input type="submit" value="提交参数param"/>
    </form><br>





</body>
</html>
