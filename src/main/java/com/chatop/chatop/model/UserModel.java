package com.chatop.chatop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String email;

    private String name;

    private String password;

    private Timestamp created_at;

    private Timestamp updated_at;
}
