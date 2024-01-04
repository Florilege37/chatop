package com.chatop.chatop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "MESSAGES")
public class MessageDB {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="rental_id")
    private Long rentalId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="message")
    private String message;

    @Column(name="created_at")
    private LocalDate createdAt = LocalDate.now();;

    @Column(name="updated_at")
    private LocalDate updatedAt = LocalDate.now();;

}
