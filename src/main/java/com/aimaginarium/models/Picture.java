package com.aimaginarium.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "images")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "s3_link")
    private String s3Link;

    @Column(name = "is_private")
    private boolean isPrivate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id")
    private UserGallery userGallery;
}
