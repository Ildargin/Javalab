package ru.itis.javalab.repositories;


public interface CookiesRepository {
    void AddAuthCookie(Long id, String value);

    Boolean CheckCookiesByID(Long id);

    Boolean CheckCookiesByValue(String value);

    Boolean CheckCookiesByIdAndValue(Long id, String value);
}
