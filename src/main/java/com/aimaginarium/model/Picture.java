package com.aimaginarium.model;

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
    private boolean privateFlag;

    @Column(name = "is_deleted")
    private boolean deletedFlag;

    @OneToOne(mappedBy = "picture", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PictureDetails pictureDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id")
    private UserGallery userGallery;
}
