package com.chatop.chatop.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private Long id;

    @Size (min = 5)
    @Email
    @NotNull
    private String email;

    @Size (min = 5)
    @NotNull
    private String name;

    @Size (min = 5)
    @NotNull
    private String password;

    private LocalDate created_at;

    private LocalDate updated_at;

}
