package com.chatop.chatop.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

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
