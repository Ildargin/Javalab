package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
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
import java.util.List;

public class ProfileServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService =  applicationContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAllUsers();
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/public/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
    }
}
