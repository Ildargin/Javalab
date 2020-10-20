package ru.itis.javalab.services;

import ru.itis.javalab.repositories.CookiesRepository;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class CookiesServiceImpl implements  CookiesService{
    private CookiesRepository cookiesRepository;

    public CookiesServiceImpl(CookiesRepository cookiesRepository) {
        this.cookiesRepository = cookiesRepository;
    }

    @Override
    public Boolean CheckCookiesByID(Long id) {
        return cookiesRepository.CheckCookiesByID(id);
    }

    @Override
    public Boolean CheckCookiesByValue(String value) {
        return cookiesRepository.CheckCookiesByValue(value);
    }

    @Override
    public void AddAuthCookieToDb(Long id, String value) {
        cookiesRepository.AddAuthCookie(id, value);
    }

    @Override
    public Optional<Cookie> findAuthCookie(Cookie[] cookieArray) {
        for (Cookie cookie : cookieArray){
            if (cookie.getName().equals("AUTH")){
                return Optional.of(cookie);
            }
        }
        return Optional.empty();
    }
}
