<%--
  Created by IntelliJ IDEA.
  User: alaska
  Date: 14.11.2020
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    body {font-family: Arial, Helvetica, sans-serif;}
    form {border: 3px solid #f1f1f1;}
    input[type=text], input[type=password] {
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      box-sizing: border-box;
    }
    button {
      background-color: #4CAF50;
      color: white;
      padding: 14px 20px;
      margin: 8px 0;
      border: none;
      cursor: pointer;
      width: 100%;
    }
    button:hover {
      opacity: 0.8;
    }
    .container {
      padding: 16px;
    }
  </style>
  <title>Login</title>
</head>
<body>

<h2>Login Form</h2>
<c:if test="${requestScope.message == 'registration'}">
  <p>The user <span style="color:green;font-size:30px;">${requestScope.username}</span> was successfully registered.</p>
</c:if>
<c:if test="${requestScope.message == 'notExist'}">
  <p>User <span style="color:red;font-size:30px;">${requestScope.username}</span> doesn't exist. Please, sign up to proceed</p>
</c:if>

<c:if test="${requestScope.message == 'incorrectPassword'}">
  <p style="color:red;font-size:30px;">Incorrect password.</p>
</c:if>

<form:form method="POST" action="/" modelAttribute="user">
  <div class="container">
    <form:label path="username"><b>Username</b></form:label>
    <form:input path="username" type="text" placeholder="Enter Username" name="username" />

    <form:label path="password"><b>Password</b> </form:label>
    <form:input path="password" type="password" placeholder="Enter Password" name="password" />
    <button type="submit">Submit</button>
  </div>
</form:form>
<a href="${pageContext.request.contextPath}/registration">Registration</a>
</body>
</html>
