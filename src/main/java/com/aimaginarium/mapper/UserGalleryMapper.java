package com.aimaginarium.mapper;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.model.UserGallery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGalleryMapper extends Mappable<UserGallery, UserGalleryDto> {
}
