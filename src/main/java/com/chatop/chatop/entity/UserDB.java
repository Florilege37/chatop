package com.chatop.chatop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "USERS")
public class UserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="created_at")
    private LocalDate createdAt = LocalDate.now();;

    @Column(name="updated_at")
    private LocalDate updatedAt = LocalDate.now();;
}
