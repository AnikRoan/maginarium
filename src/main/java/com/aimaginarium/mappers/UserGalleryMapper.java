package com.aimaginarium.mappers;

import com.aimaginarium.dtos.UserGalleryDto;
import com.aimaginarium.models.UserGallery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGalleryMapper extends Mappable<UserGallery, UserGalleryDto> {
}
