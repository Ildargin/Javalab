package ru.itis.jlab.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerMailsGeneratorImpl implements MailsGenerator {

    private final Configuration configuration;

    @Autowired
    public FreemarkerMailsGeneratorImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getMailForConfirm(String serverUrl, String confirmedCode) {
        Template confirmMailTemplate;
        configuration.setDefaultEncoding("UTF-8");
        try {
            confirmMailTemplate = configuration.getTemplate("mails/confirmed_mail.ftl");
            confirmMailTemplate.setOutputEncoding("UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Map<String, String> attributes = new HashMap<>();
        attributes.put("confirm_code", confirmedCode);
        attributes.put("server_url", serverUrl);

        StringWriter writer = new StringWriter();

        try {
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        return writer.toString();
    }

}
