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
    @NotNull
    @Range(min = 128, max = 2048, message = "Width should be between 128 and 2048")
    private Integer width;
    private Integer height;
    private String styles;
    private LocalDateTime createdAt;

    public int setPictureHeight() {
        this.height = (int) Math.round((double)this.width * 4 / 3);
        return this.height;
    }


}
