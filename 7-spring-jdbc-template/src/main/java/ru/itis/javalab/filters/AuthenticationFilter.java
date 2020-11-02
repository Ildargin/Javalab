package ru.itis.javalab.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isLoginPage = req.getRequestURI().equals("/login");
        Boolean isProfilePage = req.getRequestURI().equals("/profile");
        
        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }

        if (isLoginPage){
            if (isAuthenticated) {
                res.sendRedirect("/profile");
            } else {
                filterChain.doFilter(req, res);
            }
        }
        if (isProfilePage) {
            if (isAuthenticated) {
                filterChain.doFilter(req, res);
            } else {
                res.sendRedirect("/index");
            }
        }
    }

    @Override
    public void destroy() {

    }
}

