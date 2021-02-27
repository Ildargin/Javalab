package ru.itis.jlab.utils;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String confirmedCode);
}
