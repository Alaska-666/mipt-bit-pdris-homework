package ru.mipt.bit.homework.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private String currentUsername = "";
    private String message = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("uname");
        final String password = request.getParameter("psw");
        currentUsername = username;
        if (!Users.isUserExist(username)) {
            message = "notExist";
            doGet(request, response);
        }
        else if (!Users.isPasswordCorrect(username, password)) {
            message = "incorrectPassword";
            doGet(request, response);
        }
        else {
            request.setAttribute("username", currentUsername);
            request.getRequestDispatcher("/pageAfterLogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null && session.getAttribute("message") != null) {
            currentUsername = (String) session.getAttribute("username");
            message = (String) session.getAttribute("message");
            session.removeAttribute("username");
            session.removeAttribute("message");
        }
        request.setAttribute("username", currentUsername);
        request.setAttribute("message", message);
        currentUsername = "";
        message = "";
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
