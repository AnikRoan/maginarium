package com.aimaginarium.mapper;

import com.aimaginarium.dto.UserGalleryRequestDto;
import com.aimaginarium.model.UserGallery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGalleryRequestMapper extends Mappable<UserGallery, UserGalleryRequestDto> {
}
