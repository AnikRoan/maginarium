package com.aimaginarium.mapper;

import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.model.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PictureMapper extends Mappable<Picture, PictureDto> {
}
