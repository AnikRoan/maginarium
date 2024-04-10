package com.aimaginarium.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureDto {

    private Long id;
    private String s3Link;
    private boolean privateField;
    private boolean deleted;
    private PictureDetailsDto pictureDetailsDto;
    private UserGalleryDto userGalleryDto;


}






