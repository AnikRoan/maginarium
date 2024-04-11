package com.aimaginarium.mapper;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.model.PictureDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PictureDetailsMapper extends Mappable<PictureDetails, PictureDetailsDto> {




    @Override
    List<PictureDetailsDto> toDtos(List<PictureDetails> entities);

    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "prompt", target = "prompt")
    @Mapping(source = "width", target = "width")
    @Mapping(source = "height", target = "height")
    @Mapping(source = "styles", target = "styles")
    @Mapping(source = "picture", target = "pictureDto")
    PictureDetailsDto toDto(PictureDetails entity);
    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "prompt", target = "prompt")
    @Mapping(source = "width", target = "width")
    @Mapping(source = "height", target = "height")
    @Mapping(source = "styles", target = "styles")
    @Mapping(source = "pictureDto", target = "picture")
    PictureDetails toEntity(PictureDetailsDto dto);

    @Override
    List<PictureDetails> toEntities(List<PictureDetailsDto> dtos);
}
