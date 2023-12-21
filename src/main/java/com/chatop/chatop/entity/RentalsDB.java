package com.chatop.chatop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "RENTALS")
public class RentalsDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surface")
    private float surface;

    @Column(name="price")
    private float price;

    @Column(name="picture")
    private String picture;

    @Column(name="description")
    private String description;

    @Column(name="owner_id")
    private Long owner_id;

    @Column(name="created_at")
    private LocalDate created_at = LocalDate.now();

    @Column(name="updated_at")
    private LocalDate updated_at = LocalDate.now();
}
