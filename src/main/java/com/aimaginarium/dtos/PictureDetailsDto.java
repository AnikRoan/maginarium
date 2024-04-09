package com.aimaginarium.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
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
    private LocalDateTime createdAt;
}
