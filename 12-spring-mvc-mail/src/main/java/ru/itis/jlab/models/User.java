package ru.itis.jlab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private UUID confirmedCode;

    @Builder.Default
    private Status status = Status.NOT_CONFIRMED;

    public enum Status {
        CONFIRMED("CONFIRMED"),
        NOT_CONFIRMED("NOT_CONFIRMED");

        private final String string;
        Status(String status) {
            this.string = status;
        }
        public String getString() {
            return string;
        }
    }
}
