package com.aimaginarium.mapper;

import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.model.Picture;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PictureMapper extends Mappable<Picture, PictureDto> {


    @Override
    PictureDto toDto(Picture entity);


    @Override
    List<PictureDto> toDtos(List<Picture> entities);

   // @Mapping(source = "id",target = "id")
   // @Mapping(source = "deleted", target = "deleted")
   // @Mapping(source = "privateField", target = "privateField")
   // @Mapping(source = "s3Link", target = "s3Link")
   // @Mapping(source = "userGallery", target = "userGallery")
   // @Mapping(source = "pictureDetails", target = "pictureDetails")
    @Override
    Picture toEntity(PictureDto dto);


    @Override
    List<Picture> toEntities(List<PictureDto> dtos);


}
