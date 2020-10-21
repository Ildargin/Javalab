package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class LoginServlet extends HttpServlet {

    private UsersService usersService;
    private CookiesService cookiesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
        cookiesService = (CookiesService) servletContext.getAttribute("cookieService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Cookie[] CookiesArray = req.getCookies();
        if (cookiesService.findAuthCookie(CookiesArray).isPresent()) {
            res.sendRedirect("/profile");
        } else {
            req.getRequestDispatcher("/public/login.html").forward(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("mail").trim();
        String password = req.getParameter("password").trim();
        Optional<User> user = usersService.findUserByEmailAndPassword(new String[]{email, password});
        if (user.isPresent()) {
            Long userId = user.get().getId();
            UUID cookieId = UUID.randomUUID();
            cookiesService.AddAuthCookieToDb(userId, cookieId.toString());
            Cookie cookie = new Cookie("AUTH", cookieId.toString());
            cookie.setMaxAge(60 * 60 * 24 * 365);
            res.addCookie(cookie);
            res.sendRedirect("/profile");
        } else {
            res.sendRedirect("/signup");
        }

    }


}
