package com.aimaginarium.mapper;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.model.PictureDetails;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PictureDetailsMapper extends Mappable<PictureDetails, PictureDetailsDto> {


}
