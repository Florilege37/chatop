package com.chatop.chatop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalsModel {

    private String name;

    private float surface;

    private float price;

    private MultipartFile picture;

    private String description;

    private Long owner_id;

    private LocalDate created_at;

    private LocalDate  updated_at;
}
