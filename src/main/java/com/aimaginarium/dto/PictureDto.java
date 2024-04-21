package com.aimaginarium.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class PictureDto {

    private Long id;
    private String s3Link;
    private boolean privateFlag;
    private boolean deletedFlag;
    private PictureDetailsDto pictureDetailsDto;



}






