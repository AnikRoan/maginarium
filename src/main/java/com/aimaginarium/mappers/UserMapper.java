package com.aimaginarium.mappers;

import com.aimaginarium.dtos.UserDto;
import com.aimaginarium.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends Mappable<User, UserDto> {
}
