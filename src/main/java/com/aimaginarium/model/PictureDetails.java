package com.aimaginarium.model;

import com.aimaginarium.exception.ErrorMessage;
import com.aimaginarium.info.picture.PictureWidthSize;
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
    @Range(min = PictureWidthSize.WIDTH_MIN, max = PictureWidthSize.WIDTH_MAX,
            message = ErrorMessage.WIDTH_RANGE_MESSAGE)
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
