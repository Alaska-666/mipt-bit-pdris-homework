<%--
  Created by IntelliJ IDEA.
  User: alaska
  Date: 15.11.2020
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Audit</title>
</head>
<body>
<c:forEach items="${requestScope.logs}" var="log">
    <p>${log} <br></p>
</c:forEach>
</body>
</html>
