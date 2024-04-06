package com.aimaginarium.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_details")
public class UserDetails {
    @Id
    @Column(name ="users_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
}
