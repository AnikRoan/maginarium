package com.aimaginarium.convertors;

import com.aimaginarium.dtos.PictureDto;
import com.aimaginarium.models.Picture;
import org.springframework.stereotype.Component;

@Component
public class PictureConvertor {


    public PictureDto convertToDto(Picture picture) {
        return PictureDto.builder()
                .id(picture.getId())
                .s3Link(picture.getS3Link())
                .isPrivate(picture.isPrivate())
                .isDeleted(picture.isDeleted())
                .userGalleryDto(UserGalleryConvertor.convertToDto(picture.getUserGallery()))
                .build();
    }
    public Picture convertToEntity(PictureDto pictureDto) {
        return Picture.builder()
                .id(pictureDto.getId())
                .s3Link(pictureDto.getS3Link())
                .isPrivate(pictureDto.isPrivate())
                .isDeleted(pictureDto.isDeleted())
                .userGallery(UserGalleryConvertor.convertToEntity(pictureDto.getUserGalleryDto()))
                .build();
    }
    public Picture convertUpdateToEntity(Picture picture, PictureDto pictureDto) {
        picture.setS3Link(pictureDto.getS3Link());
        picture.setPrivate(pictureDto.isPrivate());
        picture.setDeleted(pictureDto.isDeleted());
        return picture;
    }
}
