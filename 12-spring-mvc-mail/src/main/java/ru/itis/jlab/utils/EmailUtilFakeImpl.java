package ru.itis.jlab.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.jlab.models.Email;

@Component
@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil {

    @Override
    public void sendMail(Email email) {
        System.out.println("dev");
        System.out.println(email.getSubject());
        System.out.println(email.getSender());
        System.out.println(email.getBody());
    }
}
