package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.dto.SignUpForm;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService =  applicationContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/public/signup.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SignUpForm form = new SignUpForm();
        form.setFirstName(request.getParameter("first_name"));
        form.setLastName(request.getParameter("last_name"));
        form.setEmail(request.getParameter("email"));
        form.setPassword(request.getParameter("password"));
        usersService.saveUser(form);
        response.sendRedirect("/login");

    }
}
