package com.aimaginarium.convertors;

import com.aimaginarium.dtos.PictureDetailsDto;
import com.aimaginarium.model.PictureDetails;
import org.springframework.stereotype.Component;

@Component
public class PictureDetailsConvertor {
    public PictureDetailsDto convertToDto(PictureDetails pictureDetails) {
        return PictureDetailsDto.builder()
                .id(pictureDetails.getId())
                .title(pictureDetails.getTitle())
                .prompt(pictureDetails.getPrompt())
                .width(pictureDetails.getWidth())
                .height(pictureDetails.getHeight())
                .styles(pictureDetails.getStyles())
                .createdAt(pictureDetails.getCreatedAt())
                .build();
    }
    public PictureDetails convertToEntity(PictureDetailsDto pictureDetailsDto) {
        return PictureDetails.builder()
                .id(pictureDetailsDto.getId())
                .title(pictureDetailsDto.getTitle())
                .prompt(pictureDetailsDto.getPrompt())
                .width(pictureDetailsDto.getWidth())
                .height(pictureDetailsDto.getHeight())
                .styles(pictureDetailsDto.getStyles())
                .createdAt(pictureDetailsDto.getCreatedAt())
                .build();
    }
    public PictureDetails convertUpdateToEntity(PictureDetails pictureDetails, PictureDetailsDto pictureDetailsDto) {
        pictureDetails.setTitle(pictureDetails.getTitle());
        pictureDetails.setStyles(pictureDetails.getStyles());
        return pictureDetails;

    }
}
