<%--
  Created by IntelliJ IDEA.
  User: alaska
  Date: 08.11.2020
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Registration</title>
</head>
<body>

<h2>Registration Form</h2>

<c:choose>
    <c:when test="${requestScope.username eq 'admin'}">
        <p style="color:red;font-size:50px;">You are trying to create an account with username='admin'.</p>
    </c:when>

    <c:when test="${requestScope.username ne ''}">
        <p>The user <span style="color:red;font-size:30px;">${requestScope.username}</span> is already registered. Please choose a different username.</p>
    </c:when>

    <c:otherwise>
        <form action="" method="post">
            <div class="container">
                <label>
                    <b>Username</b>
                    <input type="text" placeholder="Enter Username" name="uname" required>
                </label>
                <label>
                    <b>Password</b>
                    <input type="password" placeholder="Enter Password" name="psw" required>
                </label>
                <button type="submit">Submit</button>
            </div>
        </form>
   </c:otherwise>
</c:choose>
</body>
</html>
