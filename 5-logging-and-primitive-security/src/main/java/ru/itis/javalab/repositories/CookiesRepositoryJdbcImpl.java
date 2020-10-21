package ru.itis.javalab.repositories;

import javax.sql.DataSource;

public class CookiesRepositoryJdbcImpl implements CookiesRepository {
    private static final String SQL_CHECK_COOKIES_BY_ID = "SELECT * FROM cookies WHERE id=?";
    private static final String SQL_CHECK_COOKIES_BY_VALUE = "SELECT * FROM cookies WHERE cookie_value=?";
    private static final String SQL_CHECK_COOKIES_BY_VALUE_AND_ID = "SELECT * FROM cookies WHERE cookie_value=? AND id=?";
    private static final String SQL_SAVE_COOKIES = "INSERT INTO cookies(id, cookie_value) values (?,?)";

    private SimpleJdbcTemplate template;

    public CookiesRepositoryJdbcImpl(DataSource dataSource) {
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public Boolean CheckCookiesByID(Long id) {

        return template.checkQuery(SQL_CHECK_COOKIES_BY_ID, id);
    }

    @Override
    public Boolean CheckCookiesByValue(String value) {

        return template.checkQuery(SQL_CHECK_COOKIES_BY_VALUE, value);
    }

    @Override
    public Boolean CheckCookiesByIdAndValue(Long id, String value) {
        return template.checkQuery(SQL_CHECK_COOKIES_BY_VALUE_AND_ID, value, id);
    }

    @Override
    public void AddAuthCookie(Long id, String value) {
        template.checkQuery(SQL_SAVE_COOKIES, id, value);
    }
}
