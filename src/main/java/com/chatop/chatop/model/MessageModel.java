package com.chatop.chatop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {

    private Long rental_id;

    private Long user_id;

    private String message;

    private Timestamp created_at;

    private Timestamp updated_at;
}
