package com.aimaginarium.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class PictureDetailsDto {
    private Long id;
    private String title;
    private String prompt;
    private Integer width;
    private Integer height;
    private String styles;
    private LocalDateTime createdAt;




}
