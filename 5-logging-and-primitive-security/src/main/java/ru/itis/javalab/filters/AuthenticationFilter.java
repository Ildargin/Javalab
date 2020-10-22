package ru.itis.javalab.filters;


import ru.itis.javalab.services.CookiesService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter implements Filter {
    private CookiesService cookiesService;

    @Override
    public void init(FilterConfig config) {
        ServletContext servletContext = config.getServletContext();
        cookiesService = (CookiesService) servletContext.getAttribute("cookieService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        boolean isLoginPage = req.getRequestURI().equals("/login");
        boolean isProfilePage = req.getRequestURI().equals("/profile");

        if (isLoginPage){
            Cookie[] CookiesArray = req.getCookies();
            Optional<Cookie> authCookie = cookiesService.findAuthCookie(CookiesArray);
            Boolean cookieInDb;
            if (authCookie.isPresent()){
                cookieInDb = cookiesService.CheckCookiesByValue(authCookie.get().getValue());
                if (cookieInDb){
                    res.sendRedirect("/profile");
                } else {
                    filterChain.doFilter(req, res);
                }
            }
            else {
                filterChain.doFilter(req, res);
            }
        }

        if (isProfilePage) {
            Cookie[] CookiesArray = req.getCookies();
            Optional<Cookie> authCookie= cookiesService.findAuthCookie(CookiesArray);
            Boolean cookieInDb;
            if (authCookie.isPresent()){
                cookieInDb = cookiesService.CheckCookiesByValue(authCookie.get().getValue());
                if (cookieInDb){
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    res.sendRedirect("/login");
                }
            } else {
                res.sendRedirect("/login");
            }
        }

    }

    @Override
    public void destroy() {

    }
}

