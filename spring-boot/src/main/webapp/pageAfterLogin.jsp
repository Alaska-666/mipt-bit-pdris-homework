<%--
  Created by IntelliJ IDEA.
  User: alaska
  Date: 14.11.2020
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>General Page</title>
</head>
<body>
<p>Hello, <span style="color:green;font-size:30px;">${requestScope.username}</span></p>
</body>
</html>
