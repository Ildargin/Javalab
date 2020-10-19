package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;


public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/public/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail").trim();
        String password = request.getParameter("password").trim();

        Optional<User> user = usersService.findUserByEmailAndPassword(new String[]{email, password});
        if (user.isPresent()){
            //String color = req.getParameter("color");
            //        Cookie cookie = new Cookie("color", color);
            //        cookie.setMaxAge(60 * 60 * 24 * 365);
            //        resp.addCookie(cookie);
            //        resp.sendRedirect("/users");
            // Cookie Auth
            response.sendRedirect("/profile");
        } else{
            response.sendRedirect("/login");
        }

    }
}
