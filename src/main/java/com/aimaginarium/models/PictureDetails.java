package com.aimaginarium.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "images_details")
public class PictureDetails {
    @Id
    @Column(name = "images_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "prompt")
    private String prompt;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "styles")
    private String styles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "images_id", nullable = false)
    private Picture picture;
}
