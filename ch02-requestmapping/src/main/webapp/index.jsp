<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>第一个springmvc的项目</p>
    <p><a href="test/some.do">发起一个some.do的get请求</a></p>

    <form action="test/other.do" method="post">
        <input type="submit" value="post请求方式second.do"/>
    </form>
    <p><a href="test/thired.do">发起一个thired.do的请求</a></p>



</body>
</html>
