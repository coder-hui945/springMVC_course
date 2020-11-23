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
    <p>第一个springmvc的项目</p>
    <%--<p><a href="/ch06_path_war_exploded/user/first.do">发起一个user/first.do的get请求</a></p>--%>
    <%--此时的/ch06_path_war_exploded/不够灵活，如果改变网址，还得专门修改此处此时可以使用EL表达式来加载项目名--%>
<%--
    <p><a href="${pageContext.request.contextPath}/user/first.do">发起一个user/first.do的get请求</a></p>
--%>
    <p><a href="user/first.do">发起一个user/first.do的get请求</a></p>


</body>
</html>
