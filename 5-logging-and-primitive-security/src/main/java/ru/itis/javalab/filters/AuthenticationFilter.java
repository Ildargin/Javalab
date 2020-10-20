package ru.itis.javalab.filters;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] CookieArray = request.getCookies();
        Cookie AuthCookie = null;
        for (Cookie cookie : CookieArray) {
            if (cookie.getName().equals("AUTH")) {
                AuthCookie = cookie;
            }
        }
        if (AuthCookie != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/index");
        }
    }

    @Override
    public void destroy() {

    }
}

