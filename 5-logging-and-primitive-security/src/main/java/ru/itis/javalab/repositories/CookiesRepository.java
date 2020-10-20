package ru.itis.javalab.repositories;

import javax.servlet.http.Cookie;

public interface CookiesRepository {
    Boolean CheckCookiesByID(Long id);
    Boolean CheckCookiesByValue(String value);
    Boolean CheckCookiesByIdAndValue(Long id, String value);
    void AddAuthCookie(Long id, String value);
}
