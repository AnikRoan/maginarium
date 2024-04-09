package com.aimaginarium.mapper;

import com.aimaginarium.dto.UserProfileDto;
import com.aimaginarium.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper extends Mappable<UserProfile, UserProfileDto> {
}
