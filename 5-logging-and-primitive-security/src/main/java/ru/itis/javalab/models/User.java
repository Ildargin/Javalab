package ru.itis.javalab.models;


import lombok.*;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Integer age;
}