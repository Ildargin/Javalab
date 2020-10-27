package ru.itis.javalab.servlets;

import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/public/login.html").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("mail").trim();
        String password = req.getParameter("password").trim();
        Boolean userExist = usersService.checkUserByEmailAndPassword(email, password);
        if (userExist) {
            HttpSession session = req.getSession(true);
            session.setAttribute("authenticated", true);
            res.sendRedirect("/profile");
        } else {
            res.sendRedirect("/signup");
        }

    }


}
