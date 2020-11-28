package ru.mipt.bit.homework.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("uname");
        final String password = request.getParameter("psw");
        if (username.equalsIgnoreCase("admin") || !Users.usernameIsValid(username) || Users.isUserExist(username)) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
        else {
            Users.add(username, password);
            request.getSession().setAttribute("message", "registration");
            request.getSession().setAttribute("username", username);
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", "");
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
