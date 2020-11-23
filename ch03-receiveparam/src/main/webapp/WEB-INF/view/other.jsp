<%--
  把view视图文件放在WEB-INF目录下，防止客户直接访问，达到屏蔽的效果，只能通过开发者设计的路线访问。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>/WEB-INF/view/other.jsp从quest作用域获取数据</h3>
<h3>msg数据：${msg}</h3><br>
<h3>fun数据：${fun}</h3><br>
</body>
</html>
