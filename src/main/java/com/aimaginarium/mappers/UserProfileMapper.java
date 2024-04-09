package com.aimaginarium.mappers;

import com.aimaginarium.dtos.UserProfileDto;
import com.aimaginarium.models.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper extends Mappable<UserProfile, UserProfileDto> {
}
