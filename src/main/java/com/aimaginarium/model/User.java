package com.aimaginarium.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "is_lock")
    private Boolean isLock;


    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserProfile userProfile;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserGallery userGallery;

}
