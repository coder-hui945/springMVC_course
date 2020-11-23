<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%><%--通过标签动态获取参考地址，只针对当前页面有效--%>
<html>
<head>
    <title>Title</title>
    <%--<base href="http://localhost:8080/ch06_path_war_exploded/"/>--%><%--如果修改会比较麻烦，所以使用标签自动获取，见上--%>
    <base href="<%=basePath%>"><%--通过标签动态获取参考地址，只针对当前页面有效--%>
</head>
<body>
<p>当处理器方法返回ModelAndView实现forward转发</p>
<form action="doForward.do" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>

   <input type="submit" name="提交请求"><br>

</form><br><br>

<p>当处理器方法返回ModelAndView实现redirect重定向</p>
<form action="doRedirect.do" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>

    <input type="submit" name="提交请求"><br>

</form>
</body>
</html>
