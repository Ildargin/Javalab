@HtmlForm(method = "post", action = "/login")
public class Form {
    @HtmlInput(name = "login", placeholder = "login")
    private String login;
    @HtmlInput(name = "password", type = "email", placeholder = "password")
    private String password;
}