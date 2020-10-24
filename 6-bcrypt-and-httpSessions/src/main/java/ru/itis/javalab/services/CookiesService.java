package ru.itis.javalab.services;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface CookiesService {
    Boolean CheckCookiesByID(Long id);
    Boolean CheckCookiesByValue(String value);
    void AddAuthCookieToDb(Long id, String value);
    Optional<Cookie> findAuthCookie(Cookie[] cookieArray);
}
