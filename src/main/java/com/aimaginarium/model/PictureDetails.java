package com.aimaginarium.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;


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
    @NotNull
    @Range(min = 128, max = 2048,
            message = "Width should be between 128 and 2048")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "styles")
    private String styles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "images_id")
    private Picture picture;

}
