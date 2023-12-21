package com.chatop.chatop.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeResponse {

    private Long id;

    private String email;

    private String name;

    private LocalDate created_at;

    private LocalDate updated_at;
}
