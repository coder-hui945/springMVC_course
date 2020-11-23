<%--
  Created by IntelliJ IDEA.
  User: Terminator-hui
  Date: 2020/9/21
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
模拟推出系统，从session中删除数据。
<%
    session.removeAttribute("name");
%>

</body>
</html>
