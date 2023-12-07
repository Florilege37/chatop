package com.chatop.chatop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalsModel {
    private String name;

    private int surface;

    private int price;

    private String picture;

    private String description;

    private Long owner_id;

    private Timestamp created_at;

    private Timestamp updated_at;
}
