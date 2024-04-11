package com.aimaginarium.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureDetailsDto {

    private Long id;
    private String title;
    private String prompt;
    private Integer width;
    private Integer height;
    private String styles;
    private PictureDto pictureDto;
    private LocalDateTime createdAt;
}
