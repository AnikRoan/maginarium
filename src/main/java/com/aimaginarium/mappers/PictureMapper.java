package com.aimaginarium.mappers;

import com.aimaginarium.dtos.PictureDto;
import com.aimaginarium.models.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PictureMapper extends Mappable<Picture, PictureDto> {
}
