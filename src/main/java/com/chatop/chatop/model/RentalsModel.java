package com.chatop.chatop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalsModel {

    private String name;

    private float surface;

    private float price;

    private MultipartFile picture;

    private String description;

    private Long ownerId;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
