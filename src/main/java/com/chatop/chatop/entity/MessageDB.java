package com.chatop.chatop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "MESSAGES")
public class MessageDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="rental_id")
    private Long rental_id;

    @Column(name="user_id")
    private Long user_id;

    @Column(name="message")
    private String message;

    @Column(name="created_at")
    private Timestamp created_at;

    @Column(name="updated_at")
    private Timestamp updated_at;

}
