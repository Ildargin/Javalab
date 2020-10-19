package ru.itis.javalab.models;


import lombok.*;

@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Integer age;
}