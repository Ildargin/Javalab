package ru.itis.javalab.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/public/login.html").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        UUID uuid = UUID.randomUUID();
    }
}
