package ru.itis.javalab.filters;


import ru.itis.javalab.services.UsersService;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(false);
//        Boolean isAuthenticated = false;
//        Boolean sessionExists = session != null;
//        Boolean isLoginPage = request.getRequestURI().equals("/login");
//
//        if (sessionExists) {
//            isAuthenticated = (Boolean) session.getAttribute("authenticated");
//
//            if (isAuthenticated == null) {
//                isAuthenticated = false;
//            }
//        }
//
//        // если авторизован и запрашивает не логин или если не авторизован и запрашивает логин
//        if (isAuthenticated && !isLoginPage || !isAuthenticated && isLoginPage) {
//            filterChain.doFilter(request, response);
//        } else if (isAuthenticated && isLoginPage) {
//            // пользователь аутенцифицирован и запрашивает страницу входа
//            // - отдаем ему корень
//            response.sendRedirect("/");
//        } else {
//            // если пользователь не аутенцицицирован и запрашивает другие страницы
//            response.sendRedirect("/signIn");
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

