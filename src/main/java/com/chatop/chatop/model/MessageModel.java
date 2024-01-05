package com.chatop.chatop.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {

    @NotNull
    private Long rental_id;

    @NotNull
    private Long user_id;

    @NotNull
    @NotBlank
    private String message;

    private LocalDate created_at;

    private LocalDate  updated_at;
}
