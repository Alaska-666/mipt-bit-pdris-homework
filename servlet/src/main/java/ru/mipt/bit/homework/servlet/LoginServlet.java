package ru.mipt.bit.homework.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("uname");
        final String password = request.getParameter("psw");
        String message = "";
        if (!Users.usernameIsValid(username) || !Users.isUserExist(username)) {
            message = "notExist";
        }
        else if (!Users.isPasswordCorrect(username, password)) {
            message = "incorrectPassword";
        }
        request.setAttribute("username", username);
        request.setAttribute("message", message);
        if (message.isEmpty()) {
            request.getRequestDispatcher("/pageAfterLogin.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUsername = "";
        String message = "";
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null && session.getAttribute("message") != null) {
            currentUsername = (String) session.getAttribute("username");
            message = (String) session.getAttribute("message");
            session.removeAttribute("username");
            session.removeAttribute("message");
        }
        request.setAttribute("username", currentUsername);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
