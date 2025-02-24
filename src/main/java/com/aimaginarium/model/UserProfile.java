package com.aimaginarium.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_details")
public class UserProfile {
    @Id
    @Column(name = "users_id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "login")
    private String login;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;
}
