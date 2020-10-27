package ru.itis.javalab.servlets;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SignupServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/public/signup.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = new UserDto();
        userDto.setFirstName(request.getParameter("first_name"));
        userDto.setLastName(request.getParameter("last_name"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setEmail(request.getParameter("email"));
        usersService.saveUser(userDto);
        response.sendRedirect("public/login.html");
    }
}
