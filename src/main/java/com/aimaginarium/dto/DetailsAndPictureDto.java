package com.aimaginarium.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class DetailsAndPictureDto {

    private PictureDto pictureDto;
    private PictureDetailsDto pictureDetailsDto;

}
